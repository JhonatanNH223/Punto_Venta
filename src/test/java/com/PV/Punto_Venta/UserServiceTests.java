package com.PV.Punto_Venta;
import com.PV.Punto_Venta.dto.User.UserCreateDTO;
import com.PV.Punto_Venta.dto.User.UserResponseDTO;
import com.PV.Punto_Venta.dto.User.UserUpdateDTO;
import com.PV.Punto_Venta.exception.DuplicateException;
import com.PV.Punto_Venta.exception.InvalidDataException;
import com.PV.Punto_Venta.exception.NotFoundException;
import com.PV.Punto_Venta.model.User;
import com.PV.Punto_Venta.model.UserRol;
import com.PV.Punto_Venta.repository.UserRepository;
import com.PV.Punto_Venta.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository; // Simulamos la base de datos

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService; // Inyectamos el servicio con el repositorio falso


    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId_user(1L);
        user.setUsername("admin");
        user.setPassword("123456");
        user.setRol(UserRol.ADMIN);
    }

    //------- GetUserById ---------
    @Test
    void testGetUserById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserResponseDTO result = userService.getUserById(1L);

        System.out.println("---RESULTADO---");
        System.out.println(result);
        System.out.println("----------------");

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("admin", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_Fail() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.getUserById(99L);
        });

        System.out.println("---RESULTADO---");
        System.out.println(exception.getMessage());
        System.out.println("----------------");

        assertEquals("User Not Found: 99", exception.getMessage());
        verify(userRepository, times(1)).findById(99L);
    }

    //------- GetAllUsers ---------
    @Test
    void testGetAllUsers_Success() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.getAllUsers();
        System.out.println("---RESULTADO---");
        System.out.println(result);
        System.out.println("----------------");

        assertNotNull(result);
        verify(userRepository, times(1)).findAll();
    }

    //------- CreateUser -------
    @Test
    void testCreateUser_Success_encodePassword() {
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .username("cajero1").password("123456").rol(UserRol.USER).build();

        when(userRepository.existsByUsername("cajero1")).thenReturn(false);
        when(passwordEncoder.encode("123456")).thenReturn("HASH");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        UserResponseDTO result =  userService.createUser(userCreateDTO);

        System.out.println("---RESULTADO---");
        System.out.println(result);
        System.out.println("----------------");


        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals("HASH", captor.getValue().getPassword());
        assertNotEquals("1234", captor.getValue().getPassword());
    }

    @Test
    void testCreateUser_duplicateUsername_throwException() {
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .username("admin").password("1234").rol(UserRol.USER).build();
        when(userRepository.existsByUsername("admin")).thenReturn(true);

        DuplicateException exception = assertThrows(DuplicateException.class, () -> {
            userService.createUser(userCreateDTO);
        });

        System.out.println("---RESULTADO---");
        System.out.println(exception.getMessage());
        System.out.println("----------------");

        assertThrows(DuplicateException.class, () -> {userService.createUser(userCreateDTO);});
        verify(userRepository, never()).save(any());
    }

    @Test
    void testCreateUser_blankUsername_throwException() {
        UserCreateDTO dto = UserCreateDTO.builder()
                .username(" ").password("123456").rol(UserRol.USER).build();

        InvalidDataException exception =  assertThrows(InvalidDataException.class, () -> {
            userService.createUser(dto);
        });

        System.out.println("---RESULTADO---");
        System.out.println(exception.getMessage());
        System.out.println("----------------");

        assertThrows(InvalidDataException.class, () -> {userService.createUser(dto);});
        verify(userRepository, never()).save(any());
    }

    @Test
    void testCreateUser_nullUsername_throwException() {
        UserCreateDTO dto = UserCreateDTO.builder()
                .username(null).password("123456").rol(UserRol.USER).build();

        InvalidDataException exception =  assertThrows(InvalidDataException.class, () -> {
            userService.createUser(dto);
        });

        System.out.println("---RESULTADO---");
        System.out.println(exception.getMessage());
        System.out.println("---------------");
        assertThrows(InvalidDataException.class, () -> {userService.createUser(dto);});
        verify(userRepository, never()).save(any());
    }

    // ---- updateUser -----

    @Test
    void testUpdateUser_onlyRol(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        UserUpdateDTO dto = UserUpdateDTO.builder()
                .rol(UserRol.USER).build();

        UserResponseDTO result = userService.updateUser(1L, dto);

        System.out.println("---RESULTADO---");
        System.out.println(result);
        System.out.println("----------------");

        assertEquals(UserRol.USER, user.getRol());
        assertEquals("123456",  user.getPassword());
        verify(passwordEncoder, never()).encode(any());
    }


}
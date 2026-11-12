package com.PV.Punto_Venta.service;

import com.PV.Punto_Venta.dto.User.UserCreateDTO;
import com.PV.Punto_Venta.dto.User.UserResponseDTO;
import com.PV.Punto_Venta.dto.User.UserUpdateDTO;
import com.PV.Punto_Venta.exception.*;
import com.PV.Punto_Venta.mapper.UserMapper;
import com.PV.Punto_Venta.model.User;
import com.PV.Punto_Venta.repository.UserRepository;
import com.PV.Punto_Venta.service.Int.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponseDTO> getAllUsers() {return userRepository.findAll().stream().map(UserMapper::toResponseDTO).toList();}

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toResponseDTO).orElseThrow(() -> new NotFoundException(Entities.USER, id));
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        if (isBlank(userCreateDTO.getUsername())){throw new InvalidDataException(Entities.USER, "Username", "empty");}
        if(isBlank(userCreateDTO.getPassword())){throw new InvalidDataException(Entities.USER, "Password", "empty");}
        if (userCreateDTO.getRol() == null) {throw new InvalidDataException(Entities.USER, "Rol", "empty");}

        if (userRepository.existsByUsername(userCreateDTO.getUsername())) {
            throw new DuplicateException(Entities.USER, userCreateDTO.getUsername());
        }

        User user = UserMapper.toEntity(userCreateDTO);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        return UserMapper.toResponseDTO(userRepository.save(user));
    }


    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(Entities.USER, id));

        if (!isBlank(userUpdateDTO.getUsername()) && !userUpdateDTO.getUsername().equals(user.getUsername())){
            if(userRepository.existsByUsername(userUpdateDTO.getUsername())){
                throw new DuplicateException(Entities.USER, userUpdateDTO.getUsername());
            }
            user.setUsername(userUpdateDTO.getUsername());
        }

        if(!isBlank(userUpdateDTO.getPassword())){
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }

        if(userUpdateDTO.getRol() != null) {
            user.setRol(userUpdateDTO.getRol());
        }
        return UserMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User  user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(Entities.USER, id));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(InvalidCredentialsException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        return UserMapper.toResponseDTO(user);
    }




    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}

package com.PV.Punto_Venta.service.Int;

import com.PV.Punto_Venta.dto.User.UserCreateDTO;
import com.PV.Punto_Venta.dto.User.UserResponseDTO;
import com.PV.Punto_Venta.dto.User.UserUpdateDTO;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
    void deleteUser(Long id);

    UserResponseDTO authenticate(String username, String password);
}

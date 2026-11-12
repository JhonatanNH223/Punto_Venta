package com.PV.Punto_Venta.mapper;

import com.PV.Punto_Venta.dto.User.UserCreateDTO;
import com.PV.Punto_Venta.dto.User.UserResponseDTO;
import com.PV.Punto_Venta.model.User;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId_user());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRol(user.getRol());
        return userResponseDTO;
    }

    public static User toEntity(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setRol(userCreateDTO.getRol());
        return user;
    }
}
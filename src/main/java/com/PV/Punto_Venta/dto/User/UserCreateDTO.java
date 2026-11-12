package com.PV.Punto_Venta.dto.User;

import com.PV.Punto_Venta.model.UserRol;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserCreateDTO {
    private String username;
    private String password;
    private UserRol rol;
}


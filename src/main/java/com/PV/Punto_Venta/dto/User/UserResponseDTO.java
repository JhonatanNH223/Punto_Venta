package com.PV.Punto_Venta.dto.User;

import com.PV.Punto_Venta.model.UserRol;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
public class UserResponseDTO {
    private Long id;
    private String username;
    private UserRol rol;
}

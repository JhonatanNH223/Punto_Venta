package com.PV.Punto_Venta.config;

import com.PV.Punto_Venta.dto.User.UserCreateDTO;
import com.PV.Punto_Venta.model.UserRol;
import com.PV.Punto_Venta.repository.UserRepository;
import com.PV.Punto_Venta.service.Int.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final IUserService userService;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userService.createUser(UserCreateDTO.builder()
                    .username("admin")
                    .password("admin123")
                    .rol(UserRol.ADMIN)
                    .build());
        }
    }

}

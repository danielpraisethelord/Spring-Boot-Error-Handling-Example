package com.springboot.error.springboot_error;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.springboot.error.springboot_error.models.domain.User;

/**
 * Configuración de la aplicación.
 */
@Configuration
public class AppConfig {

    /**
     * Define una lista de usuarios como bean primario.
     * 
     * @return Lista de usuarios.
     */
    @Bean
    @Primary
    List<User> users() {
        List<User> users = Arrays.asList(
                new User(1L, "Daniel", "Santiago"),
                new User(2L, "Cristiano", "Ronaldo"),
                new User(3L, "Lionel", "Messi"),
                new User(4L, "Neymar", "Jr"));
        return users;
    }
}

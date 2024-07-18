package com.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import com.springboot.error.springboot_error.models.domain.User;

/**
 * Interfaz para el servicio de usuarios.
 */

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);
}

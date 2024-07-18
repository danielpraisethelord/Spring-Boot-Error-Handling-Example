package com.springboot.error.springboot_error.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.springboot.error.springboot_error.models.domain.User;
import com.springboot.error.springboot_error.services.UserService;

import java.util.Optional;

/**
 * Controlador principal para manejar las solicitudes a la aplicación.
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;

    /**
     * Punto de entrada principal para verificar el estado de la aplicación.
     * Provoca un error de formato de número.
     * 
     * @return Una cadena indicando el estado.
     */
    @GetMapping
    public String index() {
        // Provoca un error de formato de número.
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "ok 200";
    }

    /**
     * Muestra un usuario basado en su ID.
     * 
     * @param id El ID del usuario.
     * @return El objeto User encontrado.
     */
    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id) {
        User user = service.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Error el usuario no existe"));
        System.out.println(user.getId());
        return user;
    }

    /**
     * Segunda forma de manejar el error con un 404.
     * 
     * @param id El ID del usuario.
     * @return El objeto ResponseEntity que contiene el usuario o un estado 404.
     */
    @GetMapping("/show-v2/{id}")
    public ResponseEntity<?> showV2(@PathVariable(name = "id") Long id) {
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalUser.orElseThrow());
    }
}

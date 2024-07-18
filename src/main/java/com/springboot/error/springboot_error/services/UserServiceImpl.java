package com.springboot.error.springboot_error.services;

// import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.error.springboot_error.models.domain.User;

/**
 * Implementación del servicio de usuarios.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        // User user = null;
        // for (User u : this.users) {
        // if (u.getId().equals(id)) {
        // user = u;
        // break;
        // }
        // }

        // return Optional.ofNullable(user);

        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user;
    }

}

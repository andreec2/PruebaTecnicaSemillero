package com.prueba.semillero.service;

import com.prueba.semillero.model.User;
import com.prueba.semillero.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Guardar un usuario
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar por username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}

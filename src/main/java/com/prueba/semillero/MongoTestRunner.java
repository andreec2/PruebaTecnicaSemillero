package com.prueba.semillero;

import com.prueba.semillero.model.User;
import com.prueba.semillero.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
public class MongoTestRunner implements CommandLineRunner {

    private final UserService userService;

    public MongoTestRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        // Insertar usuario de prueba
        /* User user = new User("andres", "andres@mail.com", "1234");
        userService.saveUser(user);

        // Mostrar usuarios
        System.out.println("Usuarios en la base:");
        userService.getAllUsers().forEach(u ->
                System.out.println("- " + u.getUsername() + " (" + u.getEmail() + ")")
        );  */
    }
}

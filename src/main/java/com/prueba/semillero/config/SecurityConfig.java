package com.prueba.semillero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva protección CSRF (solo si no usas formularios web)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas las peticiones sin autenticación
                )
                .httpBasic(Customizer.withDefaults()) // Opcional: activa login básico por si necesitas testear algo
                .formLogin(form -> form.disable());   // Desactiva el login de formulario por defecto

        return http.build();
    }
}

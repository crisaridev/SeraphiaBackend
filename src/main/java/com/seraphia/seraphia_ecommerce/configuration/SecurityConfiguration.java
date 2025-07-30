package com.seraphia.seraphia_ecommerce.configuration;
//Para marcar esta clase como un archivo de configuracion de Spring

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//Crea el con constructor con Lombok
@RequiredArgsConstructor
public class SecurityConfiguration {
    //Nos va a ayudar a cifrar la password
    @Bean //Es un objeto que va crear y gestionar SpringBoot de manera automatica
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean//Lo vamos a inyectar por eso ponemos Bean ese archivo de configuracion lo va inyectar Spring asi que no hay que ponerlo en otro lado
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        //HttpSecurity lanza excepciones
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
        ).csrf(csrf -> csrf.disable());//Desactivarlo solo en pruebas
        return httpSecurity.build();//retornamos la creacion del objeto CSRF -> Es Cross Site Request Forgery lo que hace es indicar al navegador que las peticiones son confiables y pasan por un host conocido o autenticado
    }
}

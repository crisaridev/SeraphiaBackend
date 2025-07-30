package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "user")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data //Con esta notacion incluye los Getters y Setters para todos los campos
public class User {
    @Id
    //Para dejarlo autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "first_name", nullable= false, length = 50)
    @Size(max = 50, message = "FirstName debe tener maximo 50 caracteres")
    private String firstName;

    @Column(name ="last_name",nullable = false,length = 100)
    @Size(max = 100, message = "LastName debe tener maximo 100 caracteres")
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    @Size(max = 50, message = "El email debe tener máximo 50 caracteres")
    private String email;

    @Column(name = "phone", nullable = false, length = 20)
    @Size(max=20, message = "El telefono debe tener maximo 20 caracteres")
    private String phone;

    @Column(name = "password", nullable = false, length = 30)
    @Size(max = 30, message = "El password debe tener maximo 30 caracteres")
    private String password;

    @Column(name = "is_admin",nullable = false)
    private Boolean isAdmin; //true = admin | false = user

    @Column(name = "data_register", nullable = true)
    private Date dataRegister;

    @Column(name = "street",nullable = true,length = 50)
    @Size(max = 50, message = "La calle debe de tener maximo 50 caracteres")
    private String street;

    @Column(name = "num_int",nullable = true, length = 10)
    @Size(max = 10, message = "El numero interior debe de tener maximo 50 caracteres")
    private String numInt;

    @Column(name = "num_ext", nullable = true,length = 10)
    @Size(max = 10, message = "El numero exterior debe de tener maximo 10 caracteres")
    private String numExt;

    @Column(name = "suburb",nullable = true,length = 100)
    @Size(max = 100, message = "El suburb debe de tener maximo 100 caracteres")
    private String suburb;

    @Column(name = "zip_code", nullable = true, length = 10)
    @Size(max = 10, message = "El codigo postal debe de tener maximo 10 caracteres")
    private String zipCode;

    @Column(name = "city", nullable = true, length = 100)
    @Size(max = 100, message = "La ciudad debe de tener maximo 100 caracteres")
    private String city;

    @Column(name = "state", nullable = true, length = 100)
    @Size(max = 100, message = "El estado debe de tener maximo 100 caracteres")
    private String state;
}

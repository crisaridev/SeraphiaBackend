package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "status")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data //Con esta notacion incluye los Getters y Setters para todos los campos
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long idStatus;

    @Column(name = "name", nullable = false, length = 45)
    @Size(max = 45, message = "El campo name debe tener maximo 45 caracteres")
    private String name;

    @Column(name = "description", nullable = true, length = 100)
    @Size(max = 100, message = "El campo description debe tener maximo 100 caracteres")
    private String description;
}

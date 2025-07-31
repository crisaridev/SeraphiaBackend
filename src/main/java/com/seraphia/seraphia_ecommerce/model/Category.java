package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "category")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data // Con esta notacion incluye los Getters y Setters para todos los campos
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "category_name", nullable = false, length = 45)
    @Size(max = 45, message = "El campo category_name debe tener un maximo de 45 caracteres")
    private String categoryName;


}

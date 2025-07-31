package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "image")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data //Con esta notacion incluye los Getters y Setters para todos los campos
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImage;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "`order`",nullable = false)//Backticks for MySql
    private Long order;
}

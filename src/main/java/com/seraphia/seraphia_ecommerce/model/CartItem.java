package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "cartItem")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data //Con esta notacion incluye los Getters y Setters para todos los campos
public class CartItem {
    @Id
    //Para dejarlo autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartItem")
    private Long idCartItem;
}

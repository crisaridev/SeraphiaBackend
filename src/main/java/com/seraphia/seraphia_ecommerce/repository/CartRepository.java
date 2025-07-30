package com.seraphia.seraphia_ecommerce.repository;

import com.seraphia.seraphia_ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

//Recibe dos tipos de dato en <>, extiende de JpaRepository<Entity, typeId>
//1. Tipo de dato de la tabla de la cual es ese repository
//2. Es el id, el tipo de dato que tiene nuestra tabla que es un Long
public interface CartRepository extends JpaRepository<Cart, Long> {
}

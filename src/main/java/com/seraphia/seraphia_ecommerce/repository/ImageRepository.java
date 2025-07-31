package com.seraphia.seraphia_ecommerce.repository;

import com.seraphia.seraphia_ecommerce.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//Recibe dos tipos de dato en <>, extiende de JpaRepository<Entity, typeId>
//1. Tipo de dato de la tabla de la cual es ese repository
//2. Es el id, el tipo de dato que tiene nuestra tabla que es un Long
@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}

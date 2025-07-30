package com.seraphia.seraphia_ecommerce.repository;
//Esta es muy sencilla, no es obligatoria
/*
* En Spring Boot, extender la interfaz JpaRepository<Product, Long> es fundamental para
* implementar el acceso a datos (DAO - Data Access Object) de manera rápida y eficiente.
* no es necesario poner la notacion @Repository*/

import com.seraphia.seraphia_ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Lo unico que vamos hacer es extender una interfaz JpaRepository
//Recibe dos tipos de dato en <>
//1. Tipo de dato de la tabla de la cual es ese repository
//2. Es el id, el tipo de dato que tiene nuestra tabla que es un Long
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}

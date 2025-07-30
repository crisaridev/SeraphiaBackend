package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.


    //Metodo para obtener todos los productos, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Product> getAllProducts();

    //Metodo para obtener un solo objeto
    Product getProductById(Long id_product);

    //Retorno de nuestro metodo de borrar retornando un Producto. El tipo de dato es producto porque es lo que vamos a devolver
    Product deleteProductById(Long id_product);

    //Crear el producto
    List<Product> addProducts(List<Product> products);

    //Modificar producto, retornara el producto modificado
    Product updateProductById(Long id_product, Product productUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros





}

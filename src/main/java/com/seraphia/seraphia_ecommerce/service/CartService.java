package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Cart;

import java.util.List;

public interface CartService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los Carts, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Cart> getAllCarts();

    //Metodo para obtener un solo usuario
    Cart getCartById(Long id_cart);

    //Eliminar cart, para este metodo se retorna el usuario eliminado
    Cart deleteCartById(Long id_cart);

    //Crear un cart o generar carts
    List<Cart> addCarts(List<Cart> carts);

    //Modificar cart, retornando el cart modificado
    Cart updateCartById(Long id_cart, Cart cartUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros

}

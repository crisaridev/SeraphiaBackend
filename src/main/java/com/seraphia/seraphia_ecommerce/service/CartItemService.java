package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Cart;
import com.seraphia.seraphia_ecommerce.model.CartItem;

import java.util.List;

public interface CartItemService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los productos, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<CartItem> getAllCartItems();

    //Metodo para obtener un solo cartItem
    CartItem getCartItemById(Long id_cartItem);

    //Eliminar un cartItem, para este metodo se retorna el cartItem elimininado
    CartItem deleteCartItemById(Long id_cartItem);

    //Crear un cartItem o generar cartItems
    List<CartItem> addCartItems(List<CartItem> cartItems);

    //Modificar cartItem, retornando el cartItem modificado
    CartItem updateCartItemById(Long id_cartItem, CartItem cartItemUpdated);
    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

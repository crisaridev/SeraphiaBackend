package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Order;
import com.seraphia.seraphia_ecommerce.model.User;

import java.util.List;

public interface OrderService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los Order, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Order> getAllOrders();

    //Metodo para obtener un solo una Order
    Order getOrderById(Long id_order);

    //Eliminar Order, para este metodo se retorna el Order eliminado.
    Order deleteOrderById(Long id_order);

    //Crear una order o generar orders
    List<Order> addOrders(List<Order> orders);

    //Modificar Order, retornando el order modificado
    Order updateOrderById(Long id_order, Order orderUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

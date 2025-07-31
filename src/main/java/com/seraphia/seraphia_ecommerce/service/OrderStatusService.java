package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.OrderStatus;
import com.seraphia.seraphia_ecommerce.model.User;
import org.hibernate.query.Order;

import java.util.List;

public interface OrderStatusService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los OrderStatus, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<OrderStatus> getAllOrderStatus();

    //Metodo para obtener un solo usuario
    OrderStatus getOrderStatusById(Long id_order_status);

    //Eliminar OrderStatus, para este metodo se retorna el OrderStatus eliminado.
    OrderStatus deleteOrderStatusById(Long id_order_status);

    //Crear un OrderStatus o generar varias OrderStatus
    List<OrderStatus> addOrderStatuses(List<OrderStatus> orderStatuses);

    //Modificar OrderStatus, retornando el OrderStatus modificado
    OrderStatus updateOrderStatusById(Long id_order_status, OrderStatus orderStatusUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

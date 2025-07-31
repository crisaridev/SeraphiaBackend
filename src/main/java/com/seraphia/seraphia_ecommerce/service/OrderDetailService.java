package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.OrderDetail;
import com.seraphia.seraphia_ecommerce.model.User;

import java.util.List;

public interface OrderDetailService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los OrderDetails, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<OrderDetail> getAllOrderDetails();

    //Metodo para obtener un solo OrderDetail
    OrderDetail getOrderDetailById(Long id_order_detail);

    //Eliminar orderDetail, para este metodo se retorna el orderDetail eliminado.
    OrderDetail deleteOrderDetailById(Long id_order_detail);

    //Crear un OrderDetail o generar varios OrderDetails
    List<OrderDetail> addOrderDetails(List<OrderDetail> orderDetails);

    //Modificar OrderDetail, retornando el OrderDetail modificado
    OrderDetail updateOrderDetailById(Long id_order_detail, OrderDetail orderDetailUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.OrderDetail;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.service.OrderDetailService;
import com.seraphia.seraphia_ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/order-detail")// http://localhost:8080/api/order-detail
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class OrderDetailController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private OrderDetailService orderDetailService;

    //Peticion Get para obtener todos los OrderDetails
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/order-detail
    public List<OrderDetail> getAllOrderDetails(){
        return this.orderDetailService.getAllOrderDetails();
    }

    //Peticion Get para obtener un OrderDetail por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{orderDetailId}") //Vamos a mandar el id por la url //http:localhost:8080/api/order-detail/id -> // http://localhost:8080/api/order-detail/2
    //Pathvariable que de la url vamos a obtener el orderDetailId y lo pasamos como parametro a nuestro metodo
    public OrderDetail getOrderDetailById(@PathVariable("orderDetailId") Long id_order_detail){
        return this.orderDetailService.getOrderDetailById(id_order_detail);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<OrderDetail> addOrderDetails(@Valid @RequestBody List<OrderDetail> orderDetails){
        return this.orderDetailService.addOrderDetails(orderDetails);
    }

    //Peticion Delete
    @DeleteMapping(path = "{orderDetailId}")
    public OrderDetail deleteOrderDetailById(@PathVariable("orderDetailId")Long id_order_detail){
        return this.orderDetailService.deleteOrderDetailById(id_order_detail);
    }

    //Peticion Put
    @PutMapping(path = "{orderDetailId}")
    public OrderDetail updateOrderDetailById(@Valid @PathVariable("orderDetailId") Long id_order_detail, @RequestBody OrderDetail orderDetailUpdated){
        return this.orderDetailService.updateOrderDetailById(id_order_detail,orderDetailUpdated);
    }

}

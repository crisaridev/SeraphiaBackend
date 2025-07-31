package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.OrderStatus;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.service.OrderStatusService;
import com.seraphia.seraphia_ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/order-status")// http://localhost:8080/api/order-status
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class OrderStatusController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private OrderStatusService orderStatusService;

    //Peticion Get para obtener todos los Order Statuses
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/order-status
    public List<OrderStatus> getAllOrderStatus(){
        return this.orderStatusService.getAllOrderStatus();
    }

    //Peticion Get para obtener un order status por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{orderStatusId}") //Vamos a mandar el id por la url //http:localhost:8080/api/order-status/id -> // http://localhost:8080/api/order-status/2
    //Pathvariable que de la url vamos a obtener el OrderStatusId y lo pasamos como parametro a nuestro metodo
    public OrderStatus getOrderStatusById(@PathVariable("orderStatusId") Long id_order_status){
        return this.orderStatusService.getOrderStatusById(id_order_status);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<OrderStatus> addOrderStatuses(@Valid @RequestBody List<OrderStatus> orderStatuses){
        return this.orderStatusService.addOrderStatuses(orderStatuses);
    }

    //Peticion Delete
    @DeleteMapping(path = "{orderStatusId}")
    public OrderStatus deleteOrderStatusById(@PathVariable("orderStatusId")Long id_order_status){
        return this.orderStatusService.deleteOrderStatusById(id_order_status);
    }

    //Peticion Put
    @PutMapping(path = "{orderStatusId}")
    public OrderStatus updateOrderStatusById(@Valid @PathVariable("orderStatusId") Long id_order_status, @RequestBody OrderStatus orderStatusUpdated){
        return this.orderStatusService.updateOrderStatusById(id_order_status, orderStatusUpdated);
    }
}

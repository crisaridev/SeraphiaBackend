package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.Order;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.service.OrderService;
import com.seraphia.seraphia_ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/order")// http://localhost:8080/api/order
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class OrderController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private OrderService orderService;

    //Peticion Get para obtener todas las Orders
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/order
    public List<Order> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    //Peticion Get para obtener un order por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{orderId}") //Vamos a mandar el id por la url //http:localhost:8080/api/order/id -> // http://localhost:8080/api/order/2
    //Pathvariable que de la url vamos a obtener el orderId y lo pasamos como parametro a nuestro metodo
    public Order getOrderById(@PathVariable("orderId") Long id_order){
        return this.orderService.getOrderById(id_order);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<Order> addOrders(@Valid @RequestBody List<Order> orders){
        return this.orderService.addOrders(orders);
    }

    //Peticion Delete
    @DeleteMapping(path = "{orderId}")
    public Order deleteOrderById(@PathVariable("orderId")Long id_order){
        return this.orderService.deleteOrderById(id_order);
    }

    //Peticion Put
    @PutMapping(path = "{orderId}")
    public Order updateOrderById(@Valid @PathVariable("orderId") Long id_order, @RequestBody Order orderUpdated){
        return this.orderService.updateOrderById(id_order,orderUpdated);
    }
}

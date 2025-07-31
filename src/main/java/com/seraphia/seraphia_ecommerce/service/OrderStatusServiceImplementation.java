package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.OrderStatus;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.repository.OrderStatusRepository;
import com.seraphia.seraphia_ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class OrderStatusServiceImplementation implements OrderStatusService{
    //aca implementamos la interfaz, se pone la logica de nuestros metodos
//Los servicios en programacion se pone toda la logica de negocio, cosas que no tienen que ver los usuarios (expuestas). Calculos, obtener la info, etc.


//**Inyeccion de Dependencias** -> Es un patron de diseno
// Vamos a obtener el producto sin saber como se hace, solo la pedimos sin preocuparnos como consumidores
// el objetivo es Que nosotros no digamos como se construye el objeto sino que solo consumamos dicho objeto

    //El acoplamiento: es el nivel de interdependecia de las clases entre si...
    //A mayor acoplamiento es mas probable que fallen nuestros sistemas

    //A menor acoplamiento es **Cohesion** -> Es mas facil que no falle porque si realizamos un cambio como solo estamos consumiendo
    //No nos importa modificar nuestro comportamiento ya que solo lo estamos consumiendo como tal

    //Existen 2 tipos de inyeccion de dependencias en metodos y constructor
    //La que se usa en Springboot es el constructor
    //Una variable que holdee el objeto que vamos a consumir (el repository)

    private final OrderStatusRepository orderStatusRepository;
    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public OrderStatusServiceImplementation(OrderStatusRepository orderStatusRepository){
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public OrderStatus getOrderStatusById(Long id_order_status) {
        return orderStatusRepository.findById(id_order_status).orElseThrow(
                () -> new IllegalArgumentException("El Order Status con el id: " + id_order_status + " no existe")
        );
        //.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public OrderStatus deleteOrderStatusById(Long id_order_status) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar el Order Status eliminado;
        OrderStatus temporalOrderStatus = null;
        //2. Checar si el OrderStatus existe usando Early Return para evaluar si no existe el OrderStatus
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!orderStatusRepository.existsById(id_order_status)) return temporalOrderStatus;

        //Caso de exito
        temporalOrderStatus = orderStatusRepository.findById(id_order_status).get();//Con esto traemos el objeto y lo guardamos en temporalOrderStatus
        orderStatusRepository.deleteById(id_order_status); //Eliminamos el OrderStatus por el id recibido
        //Retornamos el Order Staus eliminado
        return temporalOrderStatus;
    }

    @Override
    public List<OrderStatus> addOrderStatuses(List<OrderStatus> orderStatuses) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return orderStatusRepository.saveAll(orderStatuses);
    }

    @Override
    public OrderStatus updateOrderStatusById(Long id_order_status, OrderStatus orderStatusUpdated) {
        //1. Evaluar que exista el OrderStatus de dicho id "id_order_status" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo OrderStatus
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(id_order_status);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalOrderStatus.isEmpty()) throw new IllegalArgumentException("El Order Status con el id " + id_order_status + " no existe");
        //3. Obtener el objeto original
        OrderStatus originalOrderStatus = optionalOrderStatus.get();

        //4. Evaluar el usuario que va hacer cambios, vamos a evaluar sus propiedades.
        if(orderStatusUpdated.getStatus() != null) originalOrderStatus.setStatus(orderStatusUpdated.getStatus());

        return  orderStatusRepository.save(originalOrderStatus);


    }
}

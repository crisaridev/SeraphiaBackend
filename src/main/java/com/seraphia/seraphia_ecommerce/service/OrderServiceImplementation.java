package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Order;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class OrderServiceImplementation implements OrderService{

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

    private final OrderRepository orderRepository;

    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public OrderServiceImplementation(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public Order getOrderById(Long id_order) {
        return orderRepository.findById(id_order).orElseThrow(
                () -> new IllegalArgumentException("La orden con el id " + id_order + " no existe.")
        );
        //.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public Order deleteOrderById(Long id_order) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar la order eliminado;
        Order temporalOrder = null;
        //2. Checar la order existe usando Early Return para evaluar si no existe la order
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!orderRepository.existsById(id_order)) return temporalOrder;

        //Caso de exito
        temporalOrder = orderRepository.findById(id_order).get();//Con esto traemos el objeto y lo guardamos en temporalUser
        orderRepository.deleteById(id_order); //Eliminamos la order por el id recibido
        //Retornamos el order eliminado
        return temporalOrder;


    }

    @Override
    public List<Order> addOrders(List<Order> orders) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return orderRepository.saveAll(orders);
    }

    @Override
    public Order updateOrderById(Long id_order, Order orderUpdated) {
        //1. Evaluar que exista la order de dicho id "id_order" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo Order
        Optional<Order> optionalOrder = orderRepository.findById(id_order);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalOrder.isEmpty()) throw new IllegalArgumentException("La Order con el id " + id_order + " no existe");
        //3. Obtener el objeto original
        Order originalOrder = optionalOrder.get();

        //4. Evaluar el usuario que va hacer cambios, vamos a evaluar sus propiedades.
        if(orderUpdated.getNetSale() != null) originalOrder.setNetSale(orderUpdated.getNetSale());
        if(orderUpdated.getOrderDate() != null) originalOrder.setOrderDate(orderUpdated.getOrderDate());

        return orderRepository.save(originalOrder);
    }
}

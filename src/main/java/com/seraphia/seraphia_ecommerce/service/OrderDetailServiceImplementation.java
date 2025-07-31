package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.OrderDetail;
import com.seraphia.seraphia_ecommerce.model.OrderStatus;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.repository.OrderDetailRepository;
import com.seraphia.seraphia_ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class OrderDetailServiceImplementation implements OrderDetailService {
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

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImplementation(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public OrderDetail getOrderDetailById(Long id_order_detail) {
        return orderDetailRepository.findById(id_order_detail).orElseThrow(
                () -> new IllegalArgumentException("El Order Detail con el id " + id_order_detail + " no existe.")
        );//.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public OrderDetail deleteOrderDetailById(Long id_order_detail) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar el OrderStatus eliminado;
        OrderDetail temporalOrderDetail = null;
        //2. Checar si el OrderStatus existe usando Early Return para evaluar si no existe el OrderStatus
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!orderDetailRepository.existsById(id_order_detail)) return temporalOrderDetail;

        //Caso de exito
        temporalOrderDetail = orderDetailRepository.findById(id_order_detail).get();//Con esto traemos el objeto y lo guardamos en temporalOrderDetail
        orderDetailRepository.deleteById(id_order_detail); //Eliminamos el producto por el id recibido
        //Retornamos el OrderDetail eliminado
        return temporalOrderDetail;
    }

    @Override
    public List<OrderDetail> addOrderDetails(List<OrderDetail> orderDetails) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return orderDetailRepository.saveAll(orderDetails);
    }

    @Override
    public OrderDetail updateOrderDetailById(Long id_order_detail, OrderDetail orderDetailUpdated) {
        //1. Evaluar que exista el OrderDetail de dicho id "id_order_detail" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo OrderDetail
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id_order_detail);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalOrderDetail.isEmpty()) throw new IllegalArgumentException("El Order Detail con el id " + id_order_detail + " no existe");
        //3. Obtener el objeto original
        OrderDetail originalOrderDetail = optionalOrderDetail.get();

        //4. Evaluar el usuario que va hacer cambios, vamos a evaluar sus propiedades.
        if(orderDetailUpdated.getOrderDetail() != null) originalOrderDetail.setOrderDetail(orderDetailUpdated.getOrderDetail());
        return orderDetailRepository.save(originalOrderDetail);
    }
}

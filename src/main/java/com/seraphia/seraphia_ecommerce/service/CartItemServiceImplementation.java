package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.CartItem;
import com.seraphia.seraphia_ecommerce.repository.CartItemRepository;
import com.seraphia.seraphia_ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class CartItemServiceImplementation implements CartItemService {
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

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserRepository userRepository){
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public CartItem getCartItemById(Long id_cartItem) {
        return cartItemRepository.findById(id_cartItem).orElseThrow(
                () -> new IllegalArgumentException("El cartItem con el id "+ id_cartItem + " no existe")
        );
        //.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public CartItem deleteCartItemById(Long id_cartItem) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar el cartItem eliminado;
        CartItem temporalCartItem = null;
        //2. Checar si el producto existe usando Early Return para evaluar si no existe el usuario
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!cartItemRepository.existsById(id_cartItem)) return temporalCartItem;

        //Caso exitoso
        temporalCartItem = cartItemRepository.findById(id_cartItem).get();//Con esto traemos el objeto y lo guardamos en temporalCartItem
        cartItemRepository.deleteById(id_cartItem);//Eliminamos el cartItem por el id recibido
        //Retornamos el cartItem eliminado
        return temporalCartItem;
    }

    @Override
    public List<CartItem> addCartItems(List<CartItem> cartItems) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return cartItemRepository.saveAll(cartItems);
    }

    @Override
    public CartItem updateCartItemById(Long id_cartItem, CartItem cartItemUpdated) {
        //1. Evaluar que exista el cartItem de dicho id "id_cartItem" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo CartItem
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id_cartItem);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalCartItem.isEmpty()) throw new IllegalArgumentException("El cartItem con el id " + id_cartItem + "no existe");
        //3. Obtener el objeto original
        CartItem originalCartItem = optionalCartItem.get();

        //4. Evaluar el cartItem que va hacer cambios, vamos a evaluar sus propiedades.
        return cartItemRepository.save(originalCartItem);
    }
}

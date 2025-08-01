package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Cart;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.repository.CartRepository;
import com.seraphia.seraphia_ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola
@Service
public class CartServiceImplementation  implements CartService{
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

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public CartServiceImplementation(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
        //Ir a la DB y obtener todos los carts de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON
    }

    @Override
    public Cart getCartById(Long id_cart) {
        return cartRepository.findById(id_cart).orElseThrow(
                () -> new IllegalArgumentException("El cart con el id " + id_cart + "no existe")
        );
        //.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public Cart deleteCartById(Long id_cart) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar el usuario eliminado;
        Cart temporalCart = null;
        //2. Checar si el producto existe usando Early Return para evaluar si no existe el usuario
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!cartRepository.existsById(id_cart)) return temporalCart;

        //Caso de exito
        temporalCart = cartRepository.findById(id_cart).get();//Con esto traemos el objeto y lo guardamos en temporalCart
        cartRepository.deleteById(id_cart);//Eliminamos el cart por el id recibido
        //Retornamos el cart eliminado
        return temporalCart;

    }

    @Override
    public List<Cart> addCarts(List<Cart> carts) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return cartRepository.saveAll(carts);
    }

    @Override
    public Cart updateCartById(Long id_cart, Cart cartUpdated) {
        //1. Evaluar que exista el producto de dicho id "id_user" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo User
        Optional<Cart> optionalCart = cartRepository.findById(id_cart);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalCart.isEmpty()) throw new IllegalArgumentException("El cart con el id "+ id_cart + "no existe");
        //3. Obtener el objeto original
        Cart originalCart = optionalCart.get();

        //4. Evaluar el cart que va hacer cambios, vamos a evaluar sus propiedades.
        if(cartUpdated.getDateCreation() != null) originalCart.setDateCreation(cartUpdated.getDateCreation());
        if(cartUpdated.getDateModification() != null) originalCart.setDateCreation(cartUpdated.getDateModification());

        return cartRepository.save(originalCart);
    }

    @Override
    public Cart createCartForUser(Long id_user) {
        User user = userRepository.findById(id_user)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id " + id_user + " no existe"));

        if (cartRepository.existsByUser(user)) {
            throw new RuntimeException("El usuario ya tiene un carrito");
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setDateCreation(LocalDateTime.now());
        return cartRepository.save(cart);
    }
}

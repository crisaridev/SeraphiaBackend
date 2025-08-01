package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.Cart;
import com.seraphia.seraphia_ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/cart")// http://localhost:8080/api/cart
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class CartController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private CartService cartService;

    //Peticion Get para obtener todos los carts
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/user
    public List<Cart> getAllCarts(){
        return this.cartService.getAllCarts();
    }

    //Peticion Get para obtener un cart por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{cartId}")//Vamos a mandar el id por la url //http:localhost:8080/api/user/id -> // http://localhost:8080/api/user/2
    //Pathvariable que de la url vamos a obtener el userId y lo pasamos como parametro a nuestro metodo
    public Cart getCartById(@PathVariable("cartId") Long id_cart){
        return this.cartService.getCartById(id_cart);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<Cart> addCarts(@Valid @RequestBody List <Cart> carts){
        return  this.cartService.addCarts(carts);
    }

    //Peticion Delete
    @DeleteMapping(path = "{cartId}")
    public Cart deleteCartById(@PathVariable("cartId")Long id_cart){
        return this.cartService.deleteCartById(id_cart);
    }

    //Peticion Put
    @PutMapping(path = "{cartId}")
    public Cart updateUserById(@Valid @PathVariable("cartId")Long id_cart, @RequestBody Cart cartUpdated){
        return this.cartService.updateCartById(id_cart,cartUpdated);
    }

    //Peticion Post para añadir usuario al carrito
    @PostMapping(path = "{userId}")//@RequestBody indica que la peticion tiene un body con informacion
    public Cart createCartForUser(@Valid @PathVariable("userId")Long id_user){
        return this.cartService.createCartForUser(id_user);
    }

}

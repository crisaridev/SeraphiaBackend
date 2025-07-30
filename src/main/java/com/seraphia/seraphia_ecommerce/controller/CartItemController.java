package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.CartItem;
import com.seraphia.seraphia_ecommerce.service.CartItemService;
import com.seraphia.seraphia_ecommerce.service.CartService;
import com.seraphia.seraphia_ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es RESt el controller es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticon
@RequestMapping(path = "/api/cart-item")// http://localhost:8080/api/cart-item
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class CartItemController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private CartItemService cartItemService;

    //Peticion Get para obtener todos los cartItems
    @GetMapping//url a la que se hace la peticion http://localhost:8080/api/user
    public List<CartItem> getAllCartItems(){
        return this.cartItemService.getAllCartItems();
    }

    //Peticion Get para obtener un cartItem por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{cartItemId}")//Vamos a mandar el id por la url //http:localhost:8080/api/cart-item/id -> // http://localhost:8080/api/cart-item/2
    //Pathvariable que de la url vamos a obtener el userId y lo pasamos como parametro a nuestro metodo
    public CartItem getCartItemById(@PathVariable("cartItemId")Long id_cartItem){
        return this.cartItemService.getCartItemById(id_cartItem);
    }

    //Peticion POST
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<CartItem> addCartItems(@Valid @RequestBody List<CartItem> cartItems){
        return this.cartItemService.addCartItems(cartItems);
    }

    //Peticion Delete
    @DeleteMapping(path = "{cartItemId}")
        public CartItem deleteCartItemByID(@PathVariable("cartItemId")Long id_cartItem){
            return this.cartItemService.deleteCartItemById(id_cartItem);
        }

    //Peticion PUT
    @PutMapping(path = "{cartItemId}")
    public CartItem updateCartItemById(@Valid @PathVariable("cartItemId")Long id_cartItem, @RequestBody CartItem cartItemUpdated){
        return this.cartItemService.updateCartItemById(id_cartItem,cartItemUpdated);
    }
    }



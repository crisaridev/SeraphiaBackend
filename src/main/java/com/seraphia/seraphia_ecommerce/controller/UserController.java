package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/user")// http://localhost:8080/api/user
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class UserController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private UserService userService;

    //Peticion Get para obtener todos los usuarios
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/user
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    //Peticion Get para obtener un usuario por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{userId}") //Vamos a mandar el id por la url //http:localhost:8080/api/user/id -> // http://localhost:8080/api/user/2
    //Pathvariable que de la url vamos a obtener el userId y lo pasamos como parametro a nuestro metodo
    public User getProductById(@PathVariable("userId") Long id_user){
        return this.userService.getUserById(id_user);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public User addUser(@Valid @RequestBody User user){
        return this.userService.addUser(user);
    }

    //Peticion Delete
    @DeleteMapping(path = "{userId}")
    public User deleteUserById(@PathVariable("userId")Long id_user){
        return this.userService.deleteUserById(id_user);
    }

    //Peticion Put
    @PutMapping(path = "{userId}")
    public User updateUserById(@Valid @PathVariable("userId") Long id_user, @RequestBody User userUpdated){
        return this.userService.updateUserById(id_user,userUpdated);
    }



}

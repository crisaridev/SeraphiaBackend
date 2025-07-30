package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.User;

import java.util.List;

public interface UserService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los productos, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<User> getAllUsers();

    //Metodo para obtener un solo usuario
    User getUserById(Long id_user);

    //Eliminar usuario, para este metodo se retorna el usuario eliminado.
    User deleteUserById(Long id_user);

    //Crear un usuario o generar usuarios
    List<User> addUsers(List<User> users);

    //Modificar usuario, retornando el usuario modificado
    User updateUserById(Long id_user, User userUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

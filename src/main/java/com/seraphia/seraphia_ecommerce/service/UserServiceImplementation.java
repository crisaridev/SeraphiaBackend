package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class UserServiceImplementation implements UserService{

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

    private final UserRepository userRepository;

    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public User getUserById(Long id_user) {
        return userRepository.findById(id_user).orElseThrow(
                () -> new IllegalArgumentException("El usuario con el id " + id_user + "no existe")
        );
        //.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public User deleteUserById(Long id_user) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar el usuario eliminado;
        User temporalUser = null;
        //2. Checar si el producto existe usando Early Return para evaluar si no existe el usuario
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!userRepository.existsById(id_user)) return temporalUser;

        //Caso de exito
        temporalUser = userRepository.findById(id_user).get();//Con esto traemos el objeto y lo guardamos en temporalUser
        userRepository.deleteById(id_user); //Eliminamos el producto por el id recibido
        //Retornamos el producto eliminado
        return temporalUser;
    }

    @Override
    public List<User> addUsers(List<User> users) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return userRepository.saveAll(users);
    }

    @Override
    public User updateUserById(Long id_user, User userUpdated) {
        //1. Evaluar que exista el producto de dicho id "id_user" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo User
        Optional<User> optionalUser = userRepository.findById(id_user);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalUser.isEmpty()) throw new IllegalArgumentException("El producto con el id " + id_user + "no existe");
        //3. Obtener el objeto original
        User originalUser = optionalUser.get();

        //4. Evaluar el usuario que va hacer cambios, vamos a evaluar sus propiedades.
        if(userUpdated.getFirstName() != null) originalUser.setFirstName(userUpdated.getFirstName());
        if(userUpdated.getLastName() != null) originalUser.setLastName(userUpdated.getLastName());
        if(userUpdated.getEmail() != null) originalUser.setEmail(userUpdated.getEmail());
        if(userUpdated.getPhone() != null) originalUser.setPhone(userUpdated.getPhone());
        if(userUpdated.getPassword() != null) originalUser.setPassword(userUpdated.getPassword());
        if(userUpdated.getIsAdmin() != null) originalUser.setIsAdmin(userUpdated.getIsAdmin());
        if(userUpdated.getDataRegister() != null) originalUser.setDataRegister(userUpdated.getDataRegister());
        if(userUpdated.getStreet() != null) originalUser.setStreet(userUpdated.getStreet());
        if(userUpdated.getNumInt() != null) originalUser.setNumInt(userUpdated.getNumInt());
        if(userUpdated.getNumExt() != null) originalUser.setNumExt(userUpdated.getNumExt());
        if(userUpdated.getSuburb() != null) originalUser.setSuburb(userUpdated.getSuburb());
        if(userUpdated.getZipCode() != null) originalUser.setZipCode(userUpdated.getZipCode());
        if(userUpdated.getCity() != null) originalUser.setCity(userUpdated.getCity());
        if(userUpdated.getState() != null) originalUser.setState(userUpdated.getState());
        return userRepository.save(originalUser);
    }
}

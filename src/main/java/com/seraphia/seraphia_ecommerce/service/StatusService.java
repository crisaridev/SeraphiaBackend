package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Status;
import com.seraphia.seraphia_ecommerce.model.User;

import java.util.List;

public interface StatusService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los estados, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Status> getAllStatus();

    //Metodo para obtener un solo estado
    Status getStatusById(Long id_status);

    //Eliminar estatus, para este metodo se retorna el estatus eliminado.
    Status deleteStatusById(Long id_status);

    //Crear un estado o generar estados
    List<Status> addStatus(List<Status> statuses);

    //Modificar estado, retornando el estado modificado
    Status updateStatusById(Long id_status, Status statusUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

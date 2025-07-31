package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Size;

import java.util.List;

public interface SizeService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los productos, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Size> getAllSizes();

    //Metodo para obtener una sola Size
    Size getSizeById(Long id_size);

    //Eliminar Size, para este metodo se retorna la size eliminada.
    Size deleteSizeById(Long id_size);

    //Crear un size o generar sizes
    List<Size> addSizes(List<Size> sizes);

    //Modificar size, retornando el size9 modificado
    Size updateSizeById(Long id_size, Size sizeUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros

}

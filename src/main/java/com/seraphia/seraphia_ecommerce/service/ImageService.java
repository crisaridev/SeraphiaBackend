package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Image;

import java.util.List;

public interface ImageService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los productos, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Image> getAllImages();

    //Metodo para obtener un solo una imagen
    Image getImageById(Long id_image);

    //Eliminar imagen, para este metodo se retorna la imagen eliminada.
    Image deleteImageById(Long id_image);

    //Crear una imagen o generar imagenes
    List<Image> addImages(List<Image> images);

    //Modificar imagen, retornando la imagen modificada
    Image updateImageById(Long id_image, Image imageUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

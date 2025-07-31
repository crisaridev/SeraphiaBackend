package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Image;
import com.seraphia.seraphia_ecommerce.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class ImageServiceImplementation implements ImageService{
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

    private final ImageRepository imageRepository;

    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public ImageServiceImplementation(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public Image getImageById(Long id_image) {
        return imageRepository.findById(id_image).orElseThrow(
                () -> new IllegalArgumentException("La imagen con id "+ id_image + " no existe ")
        );//.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public Image deleteImageById(Long id_image) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar la imagen eliminada;
        Image temporalImage = null;
        //2. Checar si la imagen existe usando Early Return para evaluar si no existe la imagen
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!imageRepository.existsById(id_image)) return temporalImage;

        //Caso de exito
        temporalImage = imageRepository.findById(id_image).get();//Con esto traemos el objeto y lo guardamos en temporalImage
        imageRepository.deleteById(id_image); //Eliminamos la imagen por el id recibido
        //Retornamos la imagen eliminada
        return temporalImage;

    }

    @Override
    public List<Image> addImages(List<Image> images) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return imageRepository.saveAll(images);
    }

    @Override
    public Image updateImageById(Long id_image, Image imageUpdated) {
        //1. Evaluar que exista la imagen de dicho id "id_image" que va a ser actualizada.
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo Image
        Optional<Image> optionalImage = imageRepository.findById(id_image);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalImage.isEmpty()) throw new IllegalArgumentException("La imagen con el id " + id_image + "no existe");
        //3. Obtener el objeto original
        Image originalImage = optionalImage.get();

        //4. Evaluar la imagen que va hacer cambios, vamos a evaluar sus propiedades.
        if(imageUpdated.getImageUrl() != null) originalImage.setImageUrl(imageUpdated.getImageUrl());
        if (imageUpdated.getOrder() != null) originalImage.setOrder(imageUpdated.getOrder());
        return imageRepository.save(originalImage);
    }

}

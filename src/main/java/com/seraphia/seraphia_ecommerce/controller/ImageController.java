package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.Image;
import com.seraphia.seraphia_ecommerce.service.ImageService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/image")// http://localhost:8080/api/image
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class ImageController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos imageService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz imageService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private ImageService imageService;

    //Peticion Get para obtener todas las imagenes
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/image
    public List<Image> getAllImages(){
        return this.imageService.getAllImages();
    }

    //Peticion Get para obtener una imagen por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{imageId}") //Vamos a mandar el id por la url //http:localhost:8080/api/image/id -> // http://localhost:8080/api/image/2
    //Pathvariable que de la url vamos a obtener el imageId y lo pasamos como parametro a nuestro metodo
    public Image getImageById(@PathVariable("imageId") Long id_image){
        return this.imageService.getImageById(id_image);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<Image> addImages(@RequestBody List<Image> images){
        return this.imageService.addImages(images);
    }

    //Peticion Delete
    @DeleteMapping(path = "{imageId}")
    public Image deleteImageById(@PathVariable("imageId")Long id_image){
        return this.imageService.deleteImageById(id_image);
    }

    //Peticion Put
    @PutMapping(path = "{imageId}")
    public Image updateImageById(@PathVariable("imageId") Long id_image, @RequestBody Image imageUpdated){
        return this.imageService.updateImageById(id_image,imageUpdated);
    }
}

package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.Size;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.service.SizeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/size")// http://localhost:8080/api/size
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class SizeController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private SizeService sizeService;

    //Peticion Get para obtener todos los usuarios
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/sizes
    public List<Size> getAllSizes(){
        return this.sizeService.getAllSizes();
    }

    //Peticion Get para obtener una size por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{sizeId}") //Vamos a mandar el id por la url //http:localhost:8080/api/size/id -> // http://localhost:8080/api/size/2
    //Pathvariable que de la url vamos a obtener el sizeId y lo pasamos como parametro a nuestro metodo
    public Size getSizeById(@PathVariable("sizeId") Long id_size){
        return this.sizeService.getSizeById(id_size);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<Size> addSizes(@Valid @RequestBody List<Size> sizes){
        return this.sizeService.addSizes(sizes);
    }

    //Peticion Delete
    @DeleteMapping(path = "{sizeId}")
    public Size deleteSizeById(@PathVariable("sizeId")Long id_size){
        return this.sizeService.deleteSizeById(id_size);
    }

    //Peticion Put
    @PutMapping(path = "{sizeId}")
    public Size updateSizeById(@Valid @PathVariable("sizeId") Long id_size, @RequestBody Size sizeUpdated){
        return this.sizeService.updateSizeById(id_size,sizeUpdated);
    }
}

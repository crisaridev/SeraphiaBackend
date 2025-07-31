package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.Inventory;
import com.seraphia.seraphia_ecommerce.service.InventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/inventory")// http://localhost:8080/api/inventory
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class InventoryController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos InventoryService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz inventoryService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private InventoryService inventoryService;

    //Peticion Get para obtener todos los inventarios
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/inventory
    public List<Inventory> getAllInventories(){
        return this.inventoryService.getAllInventories();
    }

    //Peticion Get para obtener un inventory por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{inventoryId}") //Vamos a mandar el id por la url //http:localhost:8080/api/inventory/id -> // http://localhost:8080/api/inventory/2
    //Pathvariable que de la url vamos a obtener el inventoryId y lo pasamos como parametro a nuestro metodo
    public Inventory getInventoryById(@PathVariable("inventoryId") Long id_inventory){
        return this.inventoryService.getInventoryById(id_inventory);
    }

    //Peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<Inventory> addInventories(@Valid @RequestBody List<Inventory> inventories){
        return this.inventoryService.addInventories(inventories);
    }

    //Peticion Delete
    @DeleteMapping(path = "{inventoryId}")
    public Inventory deleteInventoryById(@PathVariable("inventoryId")Long id_inventory){
        return this.inventoryService.deleteInventoryById(id_inventory);
    }

    //Peticion Put
    @PutMapping(path = "{inventoryId}")
    public Inventory updateInventoryById(@PathVariable("inventoryId") Long id_inventory, @Valid @RequestBody Inventory inventoryUpdated){
        return this.inventoryService.updateInventoryById(id_inventory,inventoryUpdated);
    }

}

package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Inventory;
import com.seraphia.seraphia_ecommerce.model.User;
import com.seraphia.seraphia_ecommerce.repository.InventoryRepository;
import com.seraphia.seraphia_ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class InventoryServiceImplementation implements InventoryService {
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

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImplementation(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public Inventory getInventoryById(Long id_inventory) {
        return inventoryRepository.findById(id_inventory).orElseThrow(
                () -> new IllegalArgumentException("El inventario con el id " + id_inventory + "no existe")
        );
        //.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public Inventory deleteInventoryById(Long id_inventory) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar el inventario eliminado;
        Inventory temporalInventory = null;
        //2. Checar si el inventory existe usando Early Return para evaluar si no existe el usuario
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!inventoryRepository.existsById(id_inventory)) return temporalInventory;

        //Caso de exito
        temporalInventory = inventoryRepository.findById(id_inventory).get();//Con esto traemos el objeto y lo guardamos en temporalInventory
        inventoryRepository.deleteById(id_inventory); //Eliminamos el inventario por el id recibido
        //Retornamos el inventario eliminado
        return temporalInventory;
    }

    @Override
    public List<Inventory> addInventories(List<Inventory> inventories) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return inventoryRepository.saveAll(inventories);
    }

    @Override
    public Inventory updateInventoryById(Long id_inventory, Inventory inventoryUpdated) {
        //1. Evaluar que exista el inventario de dicho id "id_inventario" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo Inventory
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id_inventory);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalInventory.isEmpty()) throw new IllegalArgumentException("El Inventario con el id " + id_inventory + "no existe");
        //3. Obtener el objeto original
        Inventory originalInventory = optionalInventory.get();

        //4. Evaluar el inventario que va hacer cambios, vamos a evaluar sus propiedades.
        if(inventoryUpdated.getSerie() != null) originalInventory.setSerie(inventoryUpdated.getSerie());
        if(inventoryUpdated.getIncomeDate() != null) originalInventory.setIncomeDate(inventoryUpdated.getIncomeDate());
        if(inventoryUpdated.getOutcomeDate() != null) originalInventory.setOutcomeDate(inventoryUpdated.getOutcomeDate());

        return inventoryRepository.save(originalInventory);

    }
}

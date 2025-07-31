package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Inventory;
import com.seraphia.seraphia_ecommerce.model.User;

import java.util.List;

public interface InventoryService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los inventarios, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Inventory> getAllInventories();

    //Metodo para obtener un solo Inventory
    Inventory getInventoryById(Long id_inventory);

    //Eliminar inventory, para este metodo se retorna el inventario eliminado.
    Inventory deleteInventoryById(Long id_inventory);

    //Crear un Inventory o generar Inventories
    List<Inventory> addInventories(List<Inventory> inventories);

    //Modificar Inventory, retornando el Inventory modificado
    Inventory updateInventoryById(Long id_inventory, Inventory inventoryUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

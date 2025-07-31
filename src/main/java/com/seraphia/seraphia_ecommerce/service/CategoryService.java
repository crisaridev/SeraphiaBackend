package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    //La interfaz definen metodos abstractos que son metodos que no tienen logica
    //simplemente la firma del metodo
    //Las clases que implementen estas interfaces dichas clases a van a tener que hacerse cargo de implementar dichos metodos.

    //Metodo para obtener todos los productos, en este caso se van a tener que guardar en una Lista de Java.util
    //Solo pone que espera que se haga no pones comportamientos en las interfaces
    List<Category> getAllCategories();

    //Metodo pata obtener una sola categoria
    Category getCategoryById(Long id_category);

    //Eliminar una categoria, para este metodo se retorna la categoria eliminada
    Category deleteCategoryById(Long id_category);

    //Crear una categoria o generar varias categorias
    List<Category> addCategories(List<Category> categories);

    //Modificar categoria, retornando la categoria modificada
    Category updateCategoryById(Long id_category, Category categoryUpdated);

    //La firma del metodo, la primera parte es el valor de retorno.
    //Segunda parte el nombre del metodo y tercera parametros
}

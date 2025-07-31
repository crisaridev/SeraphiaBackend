package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.Category;
import com.seraphia.seraphia_ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Como es rest el controler es REST
@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/category")// http://localhost:8080/api/category
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class CategoryController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos userService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz userService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private CategoryService categoryService;

    //Peticion Get para obtener todas las categorias
    @GetMapping//url a la que se hace la peticion http://localhost:8080/api/category
    public List<Category> getAllCategories(){
        return  this.categoryService.getAllCategories();
    }

    //Peticion Get para obtener un usuario por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path = "{categoryId}")
    public  Category getCategoryById(@PathVariable("categoryId")Long id_category){
        return this.categoryService.getCategoryById(id_category);
    }

    //Peticion POST
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<Category> addCategories(@Valid @RequestBody List<Category> categories){
        return categoryService.addCategories(categories);
    }

    //Peticion Delete
    @DeleteMapping(path = "{categoryId}")
    public Category deleteCategoryById(@PathVariable("categoryId")Long id_category){
        return categoryService.deleteCategoryById(id_category);
    }

    //Peticion Put
    @PutMapping(path = "{categoryId}")
    public Category updateCategoryById(@PathVariable("categoryId")Long id_category, @RequestBody Category categoryUpdated){
        return categoryService.updateCategoryById(id_category,categoryUpdated);
    }


}

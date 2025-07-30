package com.seraphia.seraphia_ecommerce.controller;

import com.seraphia.seraphia_ecommerce.model.Product;
import com.seraphia.seraphia_ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Como es rest el controler es REST

@RestController
//La etiqueta para el mapeo, con el path de la url al endpoint donde apuntamos al hacer la peticion
@RequestMapping(path = "/api/product")// http://localhost:8080/api/product
//Con Lombok podremos evitar la creacion del constructor con una etiqueta @RequiredArgsConstructor
@AllArgsConstructor
public class ProductController {
    //En el mapeo de la API tenemos que traernos los metodos que tienen la logica de nuestro Servicio
    //Para la inyeccion de dependencias implementaremos productService y no la implementacion, porque la interfaz nos indica que metodos existen
    //Si los metodos cambian su logica en el implementation, los metodos siguen siendo los mismos en la interfaz productService.
    //@Qualifiers si hay mas de una implementacion

    //inyeccion de dependencias
    private ProductService productService;

    //Peticion Get para obtener todos los productos
    @GetMapping //url a la que se hace la peticion http://localhost:8080/api/product
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    //Peticion Get para obtener un producto por id
    //En esta ocasion va a recibir un parametro entre llaves, va ir un placeholder para el valor del id
    @GetMapping(path =  "{productId}") //Vamos a mandar el id por la url //http:localhost:8080/api/product/id -> // http://localhost:8080/api/product/2
    //Pathvariable que de la url vamos a obtener el productId y lo pasamos como parametro a nuestro metodo
    public Product getProductById(@PathVariable("productId")Long id_product){
        return this.productService.getProductById(id_product);
    }

    //peticion Post
    @PostMapping//@RequestBody indica que la peticion tiene un body con informacion
    public List<Product> addProducts(@RequestBody List<Product> products){
        return this.productService.addProducts(products);
    }

    //peticion Delete
    @DeleteMapping(path = "{productId}")
    public Product deleteProductById(@PathVariable("productId")Long id_product){
        return this.productService.deleteProductById(id_product);
    }

    //PeticionPut
    @PutMapping(path = "{productId}")
    public Product updateProductById(@PathVariable("productId") Long id_product, @RequestBody Product productUpdated){
        return  this.productService.updateProductById(id_product, productUpdated);
    }



}

package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Product;
import com.seraphia.seraphia_ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class ProductServiceImplementation implements ProductService {
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

    private final ProductRepository productRepository;
    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public ProductServiceImplementation(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.

    }

    @Override
    public Product getProductById(Long id_product) {
        return productRepository.findById(id_product).orElseThrow(
                () -> new IllegalArgumentException("El producto con el id " + id_product+ "no existe")
        );
        //.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public Product deleteProductById(Long id_product) {
        //1. Devolver un objeto, para esto creamos variable temporal para guardar el producto eliminado;
        Product temporalObject = null;
        //2. Checar si el producto existe usando Early Return para evaluar si no existe el producto
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!productRepository.existsById(id_product)) return temporalObject;

        //Caso de exito!
        temporalObject = productRepository.findById(id_product).get(); //Con esto traemos el objeto y lo guardamos en tmp
        productRepository.deleteById(id_product); //Eliminamos el producto
        //Retornamos el producto eliminado
        return temporalObject;



    }

    @Override
    public List<Product> addProducts(List<Product> products) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return productRepository.saveAll(products);
    }

    @Override
    public Product updateProductById(Long id_product, Product productUpdated) {
        //1. Evaluar que exista el producto de dicho id que va ser actualizado
        //Optional es una clase que nos permite editar una excepcion de null pointer exception. tambien existe Option
        //1.1 Creamos un objeto Opcional de tipo Product
        Optional<Product> optionalProduct = productRepository.findById(id_product);
        //2. Evaluando si esta vacio pata el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalProduct.isEmpty())  throw new IllegalArgumentException("El producto con el id " + id_product+ "no existe");
        //3. Obtener el objeto original
        Product originalProduct = optionalProduct.get();

        //4. Evaluar el producto que va hacer cambios, vamos a evaluar sus propiedades.
        if(productUpdated.getName() != null) originalProduct.setName(productUpdated.getName());
        if(productUpdated.getDescription() != null) originalProduct.setDescription(productUpdated.getDescription());
        if (productUpdated.getPrice() != null) originalProduct.setPrice(productUpdated.getPrice());
        if(productUpdated.getStock() != null) originalProduct.setStock(productUpdated.getStock());
        if (productUpdated.getCreationDate() != null) originalProduct.setCreationDate(productUpdated.getCreationDate());
        if (productUpdated.getIsAvailable() != null) originalProduct.setIsAvailable(productUpdated.getIsAvailable());
        return productRepository.save(originalProduct);
}
}

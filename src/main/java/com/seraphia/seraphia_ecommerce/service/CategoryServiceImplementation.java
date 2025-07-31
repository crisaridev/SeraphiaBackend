package com.seraphia.seraphia_ecommerce.service;

import com.seraphia.seraphia_ecommerce.model.Category;
import com.seraphia.seraphia_ecommerce.repository.CategoryRepository;
import com.seraphia.seraphia_ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Tenemos que marcar la clase como un servicio, con la notacion Service.
//Despues es implementar nuestra interfaz de servicio implementandola.
@Service
public class CategoryServiceImplementation implements CategoryService {
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

    private final CategoryRepository categoryRepositoy;

    @Autowired
    //Spring < 3.2 afuerza es la notacion @autowired para decir que es una inyeccion de dependencia
    public CategoryServiceImplementation(CategoryRepository categoryRepository, UserRepository userRepository){
        this.categoryRepositoy = categoryRepository;
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepositoy.findAll();
        //Ir a la DB y obtener todos los productos de ella
        //Spring se encarga de hacer la conversion de la data de la DB a JSON.
    }

    @Override
    public Category getCategoryById(Long id_category) {
        return categoryRepositoy.findById(id_category).orElseThrow(
                () -> new IllegalArgumentException("La categoria con el id "+ id_category + " no existe")
        );//.orElseThrow -> es cuando pase una excepcion, se escribe una expresion Lambda
    }

    @Override
    public Category deleteCategoryById(Long id_category) {
        //1. Devolver un objeto, para esto creamos una variable temporal para guardar el usuario eliminado;
        Category temporalCategory = null;
        //2. Checar si el producto existe usando Early Return para evaluar si no existe el usuario
        //En caso de que no exista termina la ejecucion de la funcion en ese momento.
        //Guard Clause o Early return (Es un patron)-> Evaluar primero el caso que falla para terminar inmediatamente la ejecucion de la funcion
        if(!categoryRepositoy.existsById(id_category)) return temporalCategory;

        //Caso de exito
        temporalCategory = categoryRepositoy.findById(id_category).get();
        categoryRepositoy.deleteById(id_category);//Eliminamos el producto por el id recibido
        //Retornamos el Category eliminado
        return temporalCategory;
    }

    @Override
    public List<Category> addCategories(List<Category> categories) {
        //El metodo save sirve para
        //Actualizar: Si existe lo actualiza
        //Guardar: Si no existe lo crea
        return  categoryRepositoy.saveAll(categories);
    }

    @Override
    public Category updateCategoryById(Long id_category, Category categoryUpdated) {
        //1. Evaluar que exista el producto de dicho id "id_user" que va a ser actualizado
        //1.1 Con la clase Optional podemos editar una excepcion de null pointer exception. Tambien existe Option
        //1.1 Creamos un objeto Optional de tipo User
        Optional<Category> optionalCategory = categoryRepositoy.findById(id_category);
        //2.Evaluando si esta vacio para el Early Return, la palabra para lanzar excepciones dentro de un metodo es Throw
        if(optionalCategory.isEmpty()) throw new IllegalArgumentException("La categoria con el id " + id_category + " no existe");
        //3. Obtener el objeto original
        Category originalCategory = optionalCategory.get();

        //4. Evaluar el usuario que va hacer cambios, vamos a evaluar sus propiedades.
        if(categoryUpdated.getCategoryName() != null) originalCategory.setCategoryName(categoryUpdated.getCategoryName());
        return categoryRepositoy.save(originalCategory);

    }
}

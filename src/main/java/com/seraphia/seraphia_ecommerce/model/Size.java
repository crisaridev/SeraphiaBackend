package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "size")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data //Con esta notacion incluye los Getters y Setters para todos los campos
public class Size {
    @Id
    //Para declararlo auto incremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private Long idSize;

    @Column(name = "size_name", length = 45)
    @jakarta.validation.constraints.Size(max=45, message="SizeName debe tener maximo 45 caracteres")
    private String sizeName;
}

package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;  // Para Jakarta EE 9+


import java.math.BigDecimal;
import java.util.Date;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table( name = "product")
public class Product {
    @Id
    //para dejarlo autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name", nullable = false, length = 100)
    @Size(max = 100, message = "Name debe tener maximo 50 caracteres")
    private String name;

    @Column (name = "description", nullable = false, length = 500)
    @Size(max = 500, message = "Description debe tener maximo 500 caracteres" )
    private String description;

    @Column(name ="price", nullable = false)
    @NotNull(message = "El precio no puede ser nulo")
    @Digits(integer = 8, fraction = 2, message = "Price format invalid")
    @Min(value = 300, message = "El precio no puede ser menor de 300")
    @Max(value = 1000, message = "El precio no puede ser mayor de 1000")
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    @Min(value = 0, message = "Stock no puede ser negativo")
    private Integer stock;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "is_available", nullable = false)
    @NotNull(message = "La disponibilidad debe ser especificada (true/false)")
    private Boolean isAvailable;

    public Product(Long idProduct, String name, String description, BigDecimal price, Integer stock, Date creationDate, Boolean isAvailable) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.creationDate = creationDate;
        this.isAvailable = isAvailable;
    }

    public Product() {
        this.creationDate = new Date(); // Valor por defecto
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

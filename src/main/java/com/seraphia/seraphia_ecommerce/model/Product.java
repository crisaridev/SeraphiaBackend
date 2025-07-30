package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long id_product;

    @Column(name = "name", nullable = false)
    private String name;

    @Column (name = "description", nullable = false)
    private String description;

    @Column(name ="price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "creation_date", nullable = false)
    private Date creation_date;

    @Column(name = "is_available", nullable = false)
    private Boolean is_available;

    public Product(Long id_product, String name, String description, Double price, Integer stock, Date creation_date, Boolean is_available) {
        this.id_product = id_product;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.creation_date = creation_date;
        this.is_available = is_available;
    }

    public Product() {
        this.creation_date = new Date(); // Valor por defecto
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }
}

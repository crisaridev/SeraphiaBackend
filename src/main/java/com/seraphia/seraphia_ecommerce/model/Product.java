package com.seraphia.seraphia_ecommerce.model;

import jakarta.persistence.*;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column (name = "description", nullable = false)
    private String description;

    @Column(name ="price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    public Product(Long idProduct, String name, String description, Double price, Integer stock, Date creationDate, Boolean isAvailable) {
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

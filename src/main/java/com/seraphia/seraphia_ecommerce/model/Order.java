package com.seraphia.seraphia_ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "`order`")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data //Con esta notacion incluye los Getters y Setters para todos los campos
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name ="net_sale", nullable = false)
    @NotNull(message = "El precio no puede ser nulo")
    @Digits(integer = 8, fraction = 2, message = "Price format invalid")
    private BigDecimal netSale;

    @Column(name = "order_date", nullable = false)
    @FutureOrPresent(message = "La fecha debe ser actual o futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;
}

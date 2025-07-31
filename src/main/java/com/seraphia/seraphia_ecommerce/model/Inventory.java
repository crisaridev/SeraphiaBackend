package com.seraphia.seraphia_ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

//Indica que es una tabla
@Entity
//Nombre que le vamos a poner a la tabla
@Table(name = "inventory")
//Getters and Setters Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data //Con esta notacion incluye los Getters y Setters para todos los campos
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventory")
    private Long idInventory;

    @Column(name = "serie", nullable = false, length = 45)
    @Size(max = 45, message = "El campo serie debe ser maximo 45 caracteres")
    private String serie;

    @Column(name = "income_date", nullable = false)
    @FutureOrPresent(message = "La fecha debe ser actual o futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime incomeDate;

    @Column(name = "outcome_date", nullable = true)
    @FutureOrPresent(message = "La fecha debe ser actual o futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime outcomeDate;
}

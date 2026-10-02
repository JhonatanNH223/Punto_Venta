package com.PV.Punto_Venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "inventario")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock")
    private Long stock;

    //Muchos productos pueden tener una sola categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category_fk", nullable = true)
    private Category category;

    //Muchos productos pueden tener un solo procedimeinto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_procedure_fk", nullable = true)
    private Procedure procedure;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeProduct type;

    @Column(name = "description")
    private String description;



}

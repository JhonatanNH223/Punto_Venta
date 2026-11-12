package com.PV.Punto_Venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sales_detail")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class SalesDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sales_detail")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sale_fk", nullable = false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_fk", nullable = false)
    private Product product;

    @Column(name = "quantity", precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

}

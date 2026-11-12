package com.PV.Punto_Venta.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_card")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class InventoryCard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventory_card")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_fk", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_fk", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_movement")
    private TypeMovement typeMovement;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "previous_stock")
    private Integer previousStock;

    @Column(name = "current_stock")
    private Integer currentStock;

    @Column(name = "modify_date")
    private LocalDateTime ModifyDate;


}

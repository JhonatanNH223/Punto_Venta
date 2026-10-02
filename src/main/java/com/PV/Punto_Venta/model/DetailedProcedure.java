package com.PV.Punto_Venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DetailedProcedure")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class DetailedProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_DetailedProcedure")
    private Long id;

    //Muchos Dettales pueden estar asociados a 1 Procedimiento
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_procedure_fk",  nullable = false)
    private Procedure procedure;

    //Muchos detalles pueden estar asociadso a 1 producto
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_product_fk", nullable = false)
    private Product product;

    @Column(name = "amount", nullable = false)
    private Long amount;

}

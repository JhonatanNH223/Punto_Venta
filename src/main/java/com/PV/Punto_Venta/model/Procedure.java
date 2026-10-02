package com.PV.Punto_Venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Procedure")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_procedure")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descripcion")
    private String descripcion;

}

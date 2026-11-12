package com.PV.Punto_Venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Sale")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_fk", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cash_register_report_fk", nullable = false)
    private CashRegisterReport cashRegisterReport;

    @Column(name = "data_time")
    private LocalDateTime SaleDateTime;

    @Column(name = "total_items")
    private Long TotalItems;

    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal Total;

}

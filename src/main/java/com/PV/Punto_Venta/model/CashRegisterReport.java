package com.PV.Punto_Venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_register_report")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CashRegisterReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cash_register_report")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_user_fk", nullable = false)
    private User user;

    @Column(name = "opening_date", precision = 10, scale = 2, nullable = false)
    private LocalDateTime OpeningDate;

    @Column(name = "closing_date", precision = 10, scale = 2)
    private LocalDateTime ClosingDate;

    @Column(name = "opening_balance", precision = 10, scale = 2)
    private BigDecimal OpeningBalance;

    @Column(name = "cash_sale", precision = 10, scale = 2)
    private BigDecimal CashSale;

    @Column(name = "card_sale", precision = 10, scale = 2)
    private BigDecimal CardSale;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal Total;

    @Column(name = "declared_amount", precision = 10, scale = 2)
    private BigDecimal DeclaredAmount;

    @Column(name = "difference", precision = 10, scale = 2)
    private BigDecimal Difference;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCashRegisterReport  status;

}

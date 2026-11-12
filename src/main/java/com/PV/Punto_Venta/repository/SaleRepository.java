package com.PV.Punto_Venta.repository;

import com.PV.Punto_Venta.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}

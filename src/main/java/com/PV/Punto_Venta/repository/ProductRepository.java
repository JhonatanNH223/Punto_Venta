package com.PV.Punto_Venta.repository;

import com.PV.Punto_Venta.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

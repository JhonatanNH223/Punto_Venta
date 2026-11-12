package com.PV.Punto_Venta.repository;

import com.PV.Punto_Venta.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

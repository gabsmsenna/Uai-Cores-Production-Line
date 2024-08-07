package com.gabriel.UaiCores_ProductionLine.repository;

import com.gabriel.UaiCores_ProductionLine.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

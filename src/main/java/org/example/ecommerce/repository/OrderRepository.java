package org.example.ecommerce.repository;

import org.aspectj.weaver.ast.Or;
import org.example.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUser_IdAndActiveTrue(String userId);
}

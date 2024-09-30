package org.example.ecommerce.repository;

import org.aspectj.weaver.ast.Or;
import org.example.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUser_IdAndActiveTrue(String userId);
}

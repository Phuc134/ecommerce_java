package org.example.ecommerce.repository;

import org.example.ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrder_Id(String orderId);
}

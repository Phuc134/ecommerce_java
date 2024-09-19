package org.example.ecommerce.dto.request;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderDetailRequest {
    String orderId;
    String productId;
    Float price;
    int numberOfProducts;
    Float totalMoney;
}

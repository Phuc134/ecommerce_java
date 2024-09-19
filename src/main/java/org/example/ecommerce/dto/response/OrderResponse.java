package org.example.ecommerce.dto.response;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.entity.User;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String userId;
    String firstName;
    String email;
    String phoneNumber;
    String address;
    Date orderDate;
    Float totalMoney;
    Boolean active;
}

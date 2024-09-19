package org.example.ecommerce.dto.request;

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
public class OrderRequest {
    String userId;
    String firstName;
    String email;
    String phoneNumber;
    String address;
    Float totalMoney;
    Boolean active;
}

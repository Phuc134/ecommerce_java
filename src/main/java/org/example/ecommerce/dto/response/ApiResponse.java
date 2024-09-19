package org.example.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class ApiResponse <T>{
    int code;
    String message;
    T result;
}

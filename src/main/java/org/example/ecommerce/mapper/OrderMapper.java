package org.example.ecommerce.mapper;

import org.example.ecommerce.dto.request.OrderRequest;
import org.example.ecommerce.dto.response.OrderResponse;
import org.example.ecommerce.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderRequest request);
    OrderResponse toOrderResponse(Order order);
    @Mapping(target = "user", ignore = true)
    Order updateOrder(@MappingTarget Order order, OrderRequest request);
}

package org.example.ecommerce.mapper;

import org.example.ecommerce.dto.request.OrderDetailRequest;
import org.example.ecommerce.dto.request.OrderRequest;
import org.example.ecommerce.dto.response.OrderDetailResponse;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetail toOderDetail(OrderDetailRequest request);
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderDetail updateOrderDetail(@MappingTarget OrderDetail order, OrderDetailRequest request);
}

package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.dto.request.OrderDetailRequest;
import org.example.ecommerce.dto.request.OrderRequest;
import org.example.ecommerce.dto.response.OrderDetailResponse;
import org.example.ecommerce.exception.AppException;
import org.example.ecommerce.exception.ErrorCode;
import org.example.ecommerce.mapper.OrderDetailMapper;
import org.example.ecommerce.repository.OrderDetailRepository;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    OrderDetailMapper orderDetailMapper;
    public OrderDetailResponse createOrderDetail(OrderDetailRequest request) {
        var orderDetail = orderDetailMapper.toOderDetail(request);
        var product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        var order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        orderDetail.setProduct(product);
        orderDetail.setOrder(order);
        orderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    public OrderDetailResponse updateOrderDetail(String id, OrderDetailRequest request) {
        var orderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));
        var product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        var order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        orderDetailMapper.updateOrderDetail(orderDetail, request);
        orderDetail.setProduct(product);
        orderDetail.setOrder(order);
        orderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    public OrderDetailResponse getOrderDetail(String id) {
        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND)));
    }

    public List<OrderDetailResponse> getOrderDetailsByOrderId(String orderId) {
        return orderDetailRepository.findByOrder_Id(orderId).stream().map(orderDetailMapper::toOrderDetailResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public  void deleteOrderDetail(String id) {
        var orderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));
        orderDetailRepository.delete(orderDetail);
    }
}

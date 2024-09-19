package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.dto.request.OrderRequest;
import org.example.ecommerce.dto.response.OrderResponse;
import org.example.ecommerce.exception.AppException;
import org.example.ecommerce.exception.ErrorCode;
import org.example.ecommerce.mapper.OrderMapper;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;
    public OrderResponse createOrder(OrderRequest orderResponse) {
        var order = orderMapper.toOrder(orderResponse);
        order.setOrderDate(new Date());
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    public OrderResponse updateOrder(String id, OrderRequest orderRequest) {
        var order = orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order = orderMapper.updateOrder(order, orderRequest);
        var user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        order.setUser(user);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    public OrderResponse getOrderById(String id) {
        return orderMapper.toOrderResponse(orderRepository
                .findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND)));
    }

    public List<OrderResponse> getOrdersByUserId(String userId) {
        return orderRepository.findByUser_IdAndActiveTrue(userId).stream().map(orderMapper::toOrderResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(String id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setActive(false);
        orderRepository.save(order);
    }

}

package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.dto.request.OrderRequest;
import org.example.ecommerce.dto.response.ApiResponse;
import org.example.ecommerce.dto.response.OrderResponse;
import org.example.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(orderRequest))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable String id) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrderById(id))
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<OrderResponse>> getAllOrders(@PathVariable String userId) {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getOrdersByUserId(userId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> updateOrder(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateOrder(id, orderRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<OrderResponse> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ApiResponse.<OrderResponse>builder()
                .message("Order deleted")
                .build();
    }
}
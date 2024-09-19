package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.dto.request.OrderDetailRequest;
import org.example.ecommerce.dto.response.ApiResponse;
import org.example.ecommerce.dto.response.OrderDetailResponse;
import org.example.ecommerce.service.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-details")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @PostMapping
    public ApiResponse<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.createOrderDetail(orderDetailRequest))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderDetailResponse> getOrderDetail(@PathVariable String id) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.getOrderDetail(id))
                .build();
    }

    @GetMapping("/order/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getOrderDetailsByOrderId(orderId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDetailResponse> updateOrderDetail(@PathVariable String id, @RequestBody OrderDetailRequest orderDetailRequest) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.updateOrderDetail(id, orderDetailRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<OrderDetailResponse> deleteOrderDetail(@PathVariable String id) {
        orderDetailService.deleteOrderDetail(id);
        return ApiResponse.<OrderDetailResponse>builder()
                .message("Order detail deleted")
                .build();
    }
}
package org.example.ecommerce.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.dto.request.ProductRequest;
import org.example.ecommerce.dto.response.ApiResponse;
import org.example.ecommerce.dto.response.ProductResponse;
import org.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        return
                ApiResponse.<ProductResponse>builder()
                        .result(productService.create(product))
                        .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable String id) {
        return
                ApiResponse.<ProductResponse>builder()
                        .result(productService.getProduct(id))
                        .build();
    }
    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        return
                ApiResponse.<List<ProductResponse>>builder()
                        .result(productService.getProducts())
                        .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest product) {
        return
                ApiResponse.<ProductResponse>builder()
                        .result(productService.updateProduct(id, product))
                        .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<ProductResponse> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return  ApiResponse.<ProductResponse>builder()
                .message("Product deleted")
                .build();
    }
}

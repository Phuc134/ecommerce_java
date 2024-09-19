package org.example.ecommerce.mapper;

import org.example.ecommerce.dto.request.ProductRequest;
import org.example.ecommerce.dto.response.ProductResponse;
import org.example.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request);
    @Mapping(source = "category.id", target = "categoryId")
    ProductResponse toProductResponse(Product product);
    @Mapping(target = "category", ignore = true)
    Product updateProduct(@MappingTarget Product product,  ProductRequest request);
}

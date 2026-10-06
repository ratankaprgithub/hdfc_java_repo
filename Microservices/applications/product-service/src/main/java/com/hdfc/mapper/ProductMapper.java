package com.hdfc.mapper;

import com.hdfc.dto.ProductRequestDto;
import com.hdfc.dto.ProductResponseDto;
import com.hdfc.entity.Product;

public class ProductMapper {

    public static ProductResponseDto
    mapToProductResponseDto(
            Product product){

        if(product == null){
            return null;
        }

        return ProductResponseDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .build();
    }

    public static Product
    mapToProductEntity(
            ProductRequestDto dto){

        if(dto == null){
            return null;
        }

        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .quantity(dto.getQuantity())
                .build();
    }

}
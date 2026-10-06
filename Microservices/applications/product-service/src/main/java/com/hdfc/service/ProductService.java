package com.hdfc.service;

import java.util.List;

import com.hdfc.dto.*;

public interface ProductService {

	ProductResponseDto createProduct(ProductRequestDto dto);

	ProductResponseDto getProductById(Long id);

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

	void deleteProduct(Long id);

	ProductResponseDto addInventory(Long id, Integer quantity);

	ProductResponseDto reduceInventory(Long id, Integer quantity);
}
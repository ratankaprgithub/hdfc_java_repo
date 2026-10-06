package com.hdfc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hdfc.dto.ProductRequestDto;
import com.hdfc.dto.ProductResponseDto;
import com.hdfc.entity.Product;
import com.hdfc.exception.InsufficientInventoryException;
import com.hdfc.exception.ProductNotFoundException;
import com.hdfc.mapper.ProductMapper;
import com.hdfc.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public ProductResponseDto createProduct(ProductRequestDto dto) {

		Product product = ProductMapper.mapToProductEntity(dto);

		Product savedProduct = productRepository.save(product);

		return ProductMapper.mapToProductResponseDto(savedProduct);
	}

	@Override
	public ProductResponseDto getProductById(Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

		return ProductMapper.mapToProductResponseDto(product);
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {

		return productRepository.findAll().stream().map(ProductMapper::mapToProductResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setCategory(dto.getCategory());
		product.setQuantity(dto.getQuantity());

		Product updatedProduct = productRepository.save(product);

		return ProductMapper.mapToProductResponseDto(updatedProduct);
	}

	@Override
	public void deleteProduct(Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

		productRepository.delete(product);

	}

	@Override
	public ProductResponseDto addInventory(Long id, Integer quantity) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

		product.setQuantity(product.getQuantity() + quantity);

		Product updatedProduct = productRepository.save(product);

		return ProductMapper.mapToProductResponseDto(updatedProduct);
	}

	@Override
	public ProductResponseDto reduceInventory(Long id, Integer quantity) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

		if (product.getQuantity() < quantity) {

			throw new InsufficientInventoryException("Insufficient inventory");
		}

		product.setQuantity(product.getQuantity() - quantity);

		Product updatedProduct = productRepository.save(product);

		return ProductMapper.mapToProductResponseDto(updatedProduct);
	}

}
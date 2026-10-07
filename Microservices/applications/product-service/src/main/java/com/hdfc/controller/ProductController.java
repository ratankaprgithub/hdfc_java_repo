package com.hdfc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.InventoryRequestDto;
import com.hdfc.dto.ProductRequestDto;
import com.hdfc.dto.ProductResponseDto;
import com.hdfc.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto dto) {

		return ResponseEntity.ok(productService.createProduct(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {

		return ResponseEntity.ok(productService.getProductById(id));
	}

	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

		return ResponseEntity.ok(productService.getAllProducts());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> updateProduct(

			@PathVariable Long id,

			@Valid @RequestBody ProductRequestDto dto) {

		return ResponseEntity.ok(productService.updateProduct(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		productService.deleteProduct(id);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/inventory/add")
	public ResponseEntity<ProductResponseDto> addInventory(@PathVariable Long id,
			@Valid @RequestBody InventoryRequestDto dto) {

		return ResponseEntity.ok(productService.addInventory(id, dto.getQuantity()));
	}

	@PatchMapping("/{id}/inventory/reduce")
	public ResponseEntity<ProductResponseDto> reduceInventory(@PathVariable Long id, @Valid @RequestBody InventoryRequestDto dto) {

		return ResponseEntity.ok(productService.reduceInventory(id, dto.getQuantity()));
	}

}
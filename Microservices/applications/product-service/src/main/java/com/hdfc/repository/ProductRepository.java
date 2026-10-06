package com.hdfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
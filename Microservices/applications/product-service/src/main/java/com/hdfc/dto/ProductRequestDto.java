package com.hdfc.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

    @NotBlank(
      message = "Product name is required")
    private String name;

    @NotBlank(
      message = "Description is required")
    private String description;

    @NotNull(
      message = "Price is required")
    @Positive(
      message = "Price must be positive")
    private BigDecimal price;

    @NotBlank(
      message = "Category is required")
    private String category;

    @NotNull(
      message = "Quantity is required")
    @Min(
      value = 0,
      message = "Quantity cannot be negative")
    private Integer quantity;
}


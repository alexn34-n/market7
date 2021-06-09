package com.example.dto;

import com.example.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;

    private String name;

    private Integer price;

    public ProductDto(Product product) {
        this.id= product.getId();
        this.name= product.getName();
        this.price= product.getPrice();
    }
}

package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class OrderItemDto {

    private Long productId;

    private Integer productPrice;

    private Integer quantity;


}

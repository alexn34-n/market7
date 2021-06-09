package com.example.dto;

import com.example.component.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private List<OrderItemDto> orderItems;
    private Integer totalPrice;


}

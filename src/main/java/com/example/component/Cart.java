package com.example.component;

import com.example.dto.CartDto;
import com.example.dto.OrderItemDto;
import com.example.dto.ProductDto;
import com.example.model.OrderItem;
import com.example.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class Cart {
    private final ProductService productService;
    private final List<OrderItemDto> orderItems;
    private Integer totalPrice;

    public Cart(ProductService productService) {
        this.productService = productService;
        this.orderItems = new ArrayList<>();
        this.totalPrice = calculateTotalPrice();
    }

    public CartDto getDto(){
        return new CartDto(orderItems,totalPrice);
    }

    public void addProduct(Long id){
        final OrderItemDto orderItemDto = orderItems.stream()
                .filter(it -> it.getProductId().equals(id))
                .findFirst()
                .orElseGet(()->{
                    final ProductDto product= productService.findById(id);
                    final OrderItemDto orderItem = new OrderItemDto(product.getId(), product.getPrice(), 0);
                    orderItems.add(orderItem);
                    return orderItem;
                });
        orderItemDto.setQuantity(orderItemDto.getQuantity()+1);
      totalPrice=  calculateTotalPrice();

    }

    public void deleteProduct(Long id){

        final Optional<OrderItemDto> orderItemDto = orderItems.stream()
                .filter(it -> it.getProductId().equals(id))
                .findFirst();
        if (orderItemDto.isPresent()) {
         final OrderItemDto orderItem= orderItemDto.get();
         if(orderItem.getQuantity()==0) {
             return;
         }
            orderItem.setQuantity( orderItem.getQuantity()-1);
            totalPrice = calculateTotalPrice();
        }
    }
    public  void clear(){
        orderItems.clear();
        totalPrice=0;
    }

    private Integer calculateTotalPrice() {
        return orderItems.stream()
                .map(it->
            it.getProductPrice()* it.getQuantity())
                .reduce(0,(Integer::sum));
    }
}

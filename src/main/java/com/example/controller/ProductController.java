package com.example.controller;

import com.example.dto.ProductDto;
import com.example.model.Product;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private  final ProductService productService;
@GetMapping
    public Page<Product> findAll(@RequestParam MultiValueMap<String,String> params, Integer pageNumber){
    return productService.findAll(params,pageNumber);
    }
}

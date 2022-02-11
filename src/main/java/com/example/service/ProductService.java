package com.example.service;

import com.example.dto.ProductDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.specification.SpecificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(ProductDto::new)
                .orElseThrow(()->new ResourceNotFoundException(String.format("product with id %d not found", id)));
    }

    public Page<Product> findAll(MultiValueMap<String, String> params, Integer pageNumber) {
        return productRepository.
                findAll(SpecificationUtil.build(params), PageRequest.of(pageNumber,3));
    }
}

package com.example.service.specification;

import com.example.model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificationUtil {
    private  static  Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Product>) (root,criteriaQuery,criteriaBuilder)->criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice);
    }
    private  static  Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Product>) (root,criteriaQuery,criteriaBuilder)->criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice);
    }

    private  static  Specification<Product> titleLike(String titlePart) {
        return (Specification<Product>) (root,criteriaQuery,criteriaBuilder)->criteriaBuilder.like(root.get("title"),String.format("%%%s%%",titlePart));
    }
    public static  Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec=Specification.where(null);

        if(params.containsKey("min.price")&&!params.getFirst("min_price").isBlank()){
            spec=spec.and(SpecificationUtil.priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price"))));
        }

        if(params.containsKey("max.price")&&!params.getFirst("max_price").isBlank()){
            spec=spec.and(SpecificationUtil .priceLesserOrEqualsThan(Integer.parseInt(params.getFirst("max_price"))));
        }
        if(params.containsKey("title")&&!params.getFirst("title").isBlank()) {
            spec = spec.and(SpecificationUtil .titleLike(params.getFirst("title")));
        }
        return  spec;
    }
}

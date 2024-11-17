package com.learn.sf.service;

import com.learn.sf.entity.Product;
import com.learn.sf.repository.ProductRepository;
import com.learn.sf.specification.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceSpecification {
    private final ProductRepository productRepository;

    public List<Product> getFilteredProducts(String category, BigDecimal minPrice, BigDecimal maxPrice, Boolean availability, Boolean sortAsc) {
        Specification<Product> spec = Specification.where(null);

        if (category != null && !category.isEmpty()) {
            spec = spec.and(ProductSpecifications.hasCategory(category));
        }
        if (minPrice != null && maxPrice != null) {
            spec = spec.and(ProductSpecifications.hasPriceBetween(minPrice, maxPrice));
        }

        if (availability != null) {
            spec = spec.and(ProductSpecifications.isAvailable(availability));
        }
        return productRepository.findAll(spec);
    }

    public Page<Product> getFilteredProductsWithPaginationAndSorting(String category, BigDecimal minPrice, BigDecimal maxPrice, Boolean availability, Boolean sortAsc) {

        Specification<Product> spec = Specification.where(null);

        if (category != null && !category.isEmpty()) {
            spec = spec.and(ProductSpecifications.hasCategory(category));
        }
        if (minPrice != null && maxPrice != null) {
            spec = spec.and(ProductSpecifications.hasPriceBetween(minPrice, maxPrice));
        }

        if (availability != null) {
            spec = spec.and(ProductSpecifications.isAvailable(availability));
        }

        int pageSize = 3;
        int pageNumber = 0;

        if(sortAsc !=null){
            Sort sort = sortAsc ? Sort.by("price").ascending() : Sort.by("price").descending();
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
            return productRepository.findAll(spec, pageable);
        }else{
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return productRepository.findAll(spec, pageable);
        }
    }

}

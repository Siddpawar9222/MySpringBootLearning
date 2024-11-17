package com.learn.sf.controller;

import com.learn.sf.entity.Product;
import com.learn.sf.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/sca/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getProductsWithCriteria();
    }

    @GetMapping("/filter")
    public List<Product> getFilteredProducts(
            @RequestParam(value = "category", defaultValue = "", required = false) String category,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "availability", required = false) Boolean availability,
            @RequestParam(value = "sortAsc" ,required = false) Boolean sortAsc

    ) {
        return productService.getFilteredProductsWithSort(category, minPrice, maxPrice, availability,sortAsc);
    }
}

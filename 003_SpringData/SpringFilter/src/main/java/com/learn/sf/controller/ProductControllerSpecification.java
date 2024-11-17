package com.learn.sf.controller;

import com.learn.sf.entity.Product;
import com.learn.sf.service.ProductService;
import com.learn.sf.service.ProductServiceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/sfs/products")
@RequiredArgsConstructor
public class ProductControllerSpecification {

    private final ProductServiceSpecification specification;

    /*
     Boolean sortAsc :
     Null : fetching normal data(unsorted data)
     true : fetching data in ascending order
     false : fetching data in descending order
    */

    @GetMapping("/filter")
    public List<Product> getFilteredProducts(
            @RequestParam(value = "category", defaultValue = "", required = false) String category,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "availability", required = false) Boolean availability,
            @RequestParam(value = "sortAsc" ,required = false) Boolean sortAsc

    ) {
        return specification.getFilteredProducts(category, minPrice, maxPrice, availability, sortAsc);
    }

    @GetMapping("/filterWithPS")
    public Page<Product> getFilteredProductsWithPaginationAndSorting(
            @RequestParam(value = "category", defaultValue = "", required = false) String category,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "availability", required = false) Boolean availability,
            @RequestParam(value = "sortAsc" ,required = false) Boolean sortAsc

    ) {
        return specification.getFilteredProductsWithPaginationAndSorting(category, minPrice, maxPrice, availability, sortAsc);
    }
}

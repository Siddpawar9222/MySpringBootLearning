package com.learn.sf.specification;

import com.learn.sf.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
//    public static Specification<Product> hasCategory(String category){
//          return new Specification<Product>() {
//              @Override
//              public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                  return criteriaBuilder.equal(root.get("category"),category);
//              }
//          } ;
//    }
    public static Specification<Product> hasCategory(String category){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"),category);
    }

    public static Specification<Product> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"),minPrice,maxPrice);
    }

    public static Specification<Product> isAvailable(Boolean availability){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("availability"),availability);
    }
}

/*
 if (category == null) {
     return criteriaBuilder.conjunction(); // Always true, no filtering if category is null
 }
*/
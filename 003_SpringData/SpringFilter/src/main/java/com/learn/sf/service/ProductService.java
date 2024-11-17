package com.learn.sf.service;


import com.learn.sf.entity.Product;
import com.learn.sf.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ProductRepository productRepository;

    public Product createProduct(Product product){
          return productRepository.save(product);
    }

    public List<Product> getProductsWithCriteria() {
        // Step 1: Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Step 2: Create CriteriaQuery for the Product entity
        CriteriaQuery<Product> criteriaQuery  = criteriaBuilder.createQuery(Product.class);
        // Step 3: Define the Root for the query
        Root<Product> root  = criteriaQuery.from(Product.class);
        // For now, the query is simple and selects all products (we'll add filters in the next steps)
        criteriaQuery.select(root);
        // Execute the query and return results
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Product> getFilteredProductsWithSort(String category, BigDecimal minPrice, BigDecimal maxPrice, Boolean availability, Boolean sortAsc) {
        // Step 1: Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Step 2: Create CriteriaQuery for the Product entity
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // Step 3: Define the Root for the query
        Root<Product> root = criteriaQuery.from(Product.class);

        // Step 4: Create a list to hold predicates (conditions)
        List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

        // Step 5: Add conditions dynamically based on input
        if (category != null && !category.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("category"), category));
        }

        if (minPrice != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        if (availability != null) {
            predicates.add(criteriaBuilder.equal(root.get("availability"), availability));
        }

        // Step 6: Combine all predicates using AND
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0])));

        // Step 7: Add sorting by price (ascending or descending)
        if (sortAsc !=null  && sortAsc) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("price")));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("price")));
        }

        // Step 8: Execute the query and return results
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}

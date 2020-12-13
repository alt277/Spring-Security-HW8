package com.example.SpringSecurityHW8.repositories;

import com.example.SpringSecurityHW8.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product p WHERE p.price = (SELECT max(price) FROM Product)")
    List<Product> getProductByMaxPrice();

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.code = :code")
    Page<Product> findProductByCategoryCode(@Param("code") String code, Pageable pageable);
}

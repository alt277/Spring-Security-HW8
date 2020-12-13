package com.example.SpringSecurityHW8.repositories;

import com.example.SpringSecurityHW8.data.ProductData;
import com.example.SpringSecurityHW8.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDataRepository extends JpaRepository<Product, Long> {

    @Query("select new com.example.SpringSecurityHW8.data.ProductData(p.id, p.title, p.brandName, p.price, p.createDate, p.modifyDate) from Product p")
    List<ProductData> findAllProductData();

}

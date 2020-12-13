package com.example.SpringSecurityHW8.repositories;

import com.example.SpringSecurityHW8.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("select o from Order o where o.user.id = ?1")
    List<Order> findAllByUserId(Long userId);
}

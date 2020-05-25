package com.project.ordertracker.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.project.ordertracker.jpa.data.CustomerOrders;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Long> {
    List<CustomerOrders> findAllByCustomerIdAndOrderStatus(Long customerId, String orderStatus);
}
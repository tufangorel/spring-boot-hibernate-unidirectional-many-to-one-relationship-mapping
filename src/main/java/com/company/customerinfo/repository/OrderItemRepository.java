package com.company.customerinfo.repository;

import com.company.customerinfo.model.Customer;
import com.company.customerinfo.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query("SELECT o.customerOrder.customer FROM OrderItem o WHERE o.id = :orderItemID")
    Customer findCustomerByOrderItemID(Integer orderItemID);

}


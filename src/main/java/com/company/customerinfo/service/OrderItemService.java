package com.company.customerinfo.service;


import com.company.customerinfo.model.Customer;
import com.company.customerinfo.model.CustomerOrder;
import com.company.customerinfo.model.OrderItem;
import com.company.customerinfo.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> findByID(Integer id){
        return orderItemRepository.findById(id);
    }

    public Customer findCustomerByOrderItemID(Integer id){
        return orderItemRepository.findCustomerByOrderItemID(id);
    }

    public Iterable<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public void deleteOrderItemByID(Integer id) {
        orderItemRepository.deleteById(id);
    }
}
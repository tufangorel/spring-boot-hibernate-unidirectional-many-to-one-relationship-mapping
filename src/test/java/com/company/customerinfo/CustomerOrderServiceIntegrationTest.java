package com.company.customerinfo;


import com.company.customerinfo.model.Customer;
import com.company.customerinfo.model.CustomerOrder;
import com.company.customerinfo.model.OrderItem;
import com.company.customerinfo.service.CustomerOrderService;
import com.company.customerinfo.service.CustomerService;
import com.company.customerinfo.service.OrderItemService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = CustomerInfoApplication.class)
@ActiveProfiles("dev")
public class CustomerOrderServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private OrderItemService orderItemService;


    @Order(1)
    @Test
    public void saveCustomerWithOrderTest() {

        Customer customer = new Customer();
        customer.setName("name1");
        customer.setAge(1);

        Customer savedCustomerRecord = customerService.save(customer);
        assertThat( savedCustomerRecord.getShippingAddress() != null);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setOrderDate(LocalDateTime.now());

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setCustomerOrder(customerOrder);
        orderItem1.setQuantity(1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setCustomerOrder(customerOrder);
        orderItem2.setQuantity(2);

        CustomerOrder savedCustomerOrder = customerOrderService.save(customerOrder);
        assertThat( savedCustomerOrder != null);

        orderItemService.save(orderItem1);
        orderItemService.save(orderItem2);

        Optional<OrderItem> orderItem1FromRepo = orderItemService.findByID(orderItem1.getId());
        Optional<OrderItem> orderItem2FromRepo = orderItemService.findByID(orderItem2.getId());
        assertThat( orderItem1FromRepo.get().getCustomerOrder() == orderItem2FromRepo.get().getCustomerOrder() ) ;

        Customer customerHavingTheOrderItem1 = orderItemService.findCustomerByOrderItemID(orderItem1.getId());
        Customer customerHavingTheOrderItem2 = orderItemService.findCustomerByOrderItemID(orderItem2.getId());
        assertThat( customerHavingTheOrderItem1 != null);
        assertThat( customerHavingTheOrderItem1.getName() == savedCustomerRecord.getName());
        assertThat( customerHavingTheOrderItem2 != null);
        assertThat( customerHavingTheOrderItem2.getName() == savedCustomerRecord.getName());
    }
}

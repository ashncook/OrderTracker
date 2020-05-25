package com.project.ordertracker.services;

import java.util.List;

import com.project.ordertracker.dto.OrderDTO;
import com.project.ordertracker.jpa.data.CustomerOrders;
import com.project.ordertracker.jpa.repository.CustomerOrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@RestController("/api")
public class OrderController {
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    private static final String ORDER_PLACED = "ORDER_PLACED";

    private static final String ORDER_IN_PROGRESS = "ORDER_IN_PROGRESS";

    private static final String COMPLETED_ORDERS = "ORDER_COMPLETE";

    @PostMapping(value="/placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody OrderDTO placedOrder) {
        Long customerId = placedOrder.getCustomerId();
        Long recipeId = placedOrder.getRecipeId();
        String comments = placedOrder.getComments();

        CustomerOrders customerOrder = new CustomerOrders();
        customerOrder.setCustomerId(customerId);
        customerOrder.setRecipeId(recipeId);
        customerOrder.setOrderStatus(ORDER_PLACED);
        customerOrder.setAdditionalComments(comments);
        customerOrdersRepository.save(customerOrder);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @GetMapping(value="/getOngoingOrders")
    public ResponseEntity<Object> getOngoingOrders(@RequestParam Long customerId) {

        List<CustomerOrders> ongoingOrders = customerOrdersRepository.findAllByCustomerIdAndOrderStatus(customerId, ORDER_IN_PROGRESS);

        return new ResponseEntity<Object>(ongoingOrders, HttpStatus.OK);
    }



    @GetMapping(value="/getCompletedOrders")
    public ResponseEntity<Object> getCompletedOrders(@RequestParam Long customerId) {

        List<CustomerOrders> completedOrders = customerOrdersRepository.findAllByCustomerIdAndOrderStatus(customerId, COMPLETED_ORDERS);

        return new ResponseEntity<Object>(completedOrders, HttpStatus.OK);
    }
    
}
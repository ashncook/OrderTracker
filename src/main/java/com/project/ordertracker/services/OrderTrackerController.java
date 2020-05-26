package com.project.ordertracker.services;

import java.util.List;

import com.project.ordertracker.dto.OrderDTO;
import com.project.ordertracker.jpa.data.CustomerOrders;
import com.project.ordertracker.jpa.repository.CustomerOrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Component
@RestController
public class OrderTrackerController {
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    private static final String ORDER_PLACED = "ORDER_PLACED";

    private static final String ORDER_IN_PROGRESS = "ORDER_IN_PROGRESS";

    private static final String COMPLETED_ORDERS = "ORDER_COMPLETE";

    @PostMapping(path="/placeOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> placeOrder(@RequestBody OrderDTO placedOrder) {
        Long customerId = placedOrder.getCustomerId();
        Long recipeId = placedOrder.getRecipeId();
        String comments = placedOrder.getComments();
        log.info("Placing order for customerId: {} with RecipeId: {}", customerId, recipeId);

        CustomerOrders customerOrder = new CustomerOrders();
        customerOrder.setCustomerId(customerId);
        customerOrder.setRecipeId(recipeId);
        customerOrder.setOrderStatus(ORDER_PLACED);
        customerOrder.setAdditionalComments(comments);
        customerOrdersRepository.save(customerOrder);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @GetMapping(path="/getPlacedOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPlacedOrders(@RequestParam Long customerId) {
        log.info("Retrieving Placed orders for CustomerId {}", customerId);
        List<CustomerOrders> ongoingOrders = customerOrdersRepository.findAllByCustomerIdAndOrderStatus(customerId, ORDER_PLACED);

        return new ResponseEntity<Object>(ongoingOrders, HttpStatus.OK);
    }


    @GetMapping(path="/getOngoingOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOngoingOrders(@RequestParam Long customerId) {
        log.info("Retrieving OnGoing orders for CustomerId {}", customerId);
        List<CustomerOrders> ongoingOrders = customerOrdersRepository.findAllByCustomerIdAndOrderStatus(customerId, ORDER_IN_PROGRESS);

        return new ResponseEntity<Object>(ongoingOrders, HttpStatus.OK);
    }


    @GetMapping(path="/getCompletedOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCompletedOrders(@RequestParam Long customerId) {
        log.info("Retrieving Completed orders for CustomerId {}", customerId);
        List<CustomerOrders> completedOrders = customerOrdersRepository.findAllByCustomerIdAndOrderStatus(customerId, COMPLETED_ORDERS);

        return new ResponseEntity<Object>(completedOrders, HttpStatus.OK);
    }
    
}
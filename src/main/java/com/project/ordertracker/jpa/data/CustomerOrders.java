package com.project.ordertracker.jpa.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOMER_ORDERS", schema = "ORDER_PROCESSING")
public class CustomerOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIME_ORDER_PLACED")
    private Timestamp timeOrderPlaced;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "RECIPE_ID")
    private Long recipeId;

    @Column(name = "TIME_ORDER_UPDATED", columnDefinition = "CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp timeOrderUpdated;

    @Column(name = "ADDITIONAL_COMMENTS", columnDefinition = "mediumtext")
    private String additionalComments;

    @Column(name = "ORDER_STATUS", length = 50)
    private String orderStatus;
}
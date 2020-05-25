package com.project.ordertracker.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long customerId;

    private Long recipeId;

    private String comments;
}
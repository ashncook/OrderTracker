package com.project.ordertracker.jpa.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "RECIPES", schema = "ORDER_PROCESSING")
public class Recipes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "RECIPE_ID")
    private Long recipeId;

    @Column(name = "RECIPE_NAME", length = 50)
    private String name;

    @Column(name = "INGREDIANT", length = 100)
    private String ingrediant;

    @Column(name = "MEASUREMENT", length = 20)
    private String measurement;
}
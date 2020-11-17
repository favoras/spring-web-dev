package com.myspringweb.springwebapp.product.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private long id;
    private String name;
    private Integer inStock;
    private BigDecimal price;
    private String description;

}

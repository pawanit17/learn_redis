package io.fireacademy.redisconnectivity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String id;

    private String name;

    private int quantity;

    private double cost;
}

package com.jmreisswitz.aegromini.adapters.delivery.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductivityRest implements Serializable {
    private String productionType;
    private double productivityAmount;
}

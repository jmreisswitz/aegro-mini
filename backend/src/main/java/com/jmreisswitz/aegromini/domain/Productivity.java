package com.jmreisswitz.aegromini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Productivity {
    private String productionType;
    private double productivityAmount;
}

package com.jmreisswitz.aegromini.adapters.delivery.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductionRest implements Serializable {
    String id;
    @NonNull String fieldId;
    String productionType;
    Double productionAmount;
}

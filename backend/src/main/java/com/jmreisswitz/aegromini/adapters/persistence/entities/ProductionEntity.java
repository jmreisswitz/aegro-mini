package com.jmreisswitz.aegromini.adapters.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductionEntity implements Serializable {
    String id;
    @NonNull String fieldId;
    String productionType;
    Double productionAmount;
}

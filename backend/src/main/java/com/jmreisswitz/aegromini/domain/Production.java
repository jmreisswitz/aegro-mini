package com.jmreisswitz.aegromini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Production implements Serializable {
    String id;
    @NonNull String fieldId;
    String productionType;
    Double productionAmount;
}

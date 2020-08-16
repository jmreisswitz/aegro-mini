package com.jmreisswitz.aegromini.adapters.delivery.rest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldRest implements Serializable {
    private String id;

    @NonNull
    private String farmId;

    @NonNull
    private String name;

    @NonNull
    private double area;
}

package com.jmreisswitz.aegromini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Field implements Serializable {
    private String id;
    @NonNull private String farmId;
    private String name;
    @NonNull private double area;
}

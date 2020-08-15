package com.jmreisswitz.aegromini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
public class Field implements Serializable {
    private String name;
    @NonNull private String farmId;
    @NonNull private String id;
    @NonNull private double area;
    private HashMap<String, Production> productions;
}

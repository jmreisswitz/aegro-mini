package com.jmreisswitz.aegromini.adapters.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@AllArgsConstructor
@Data
public class FieldEntity implements Serializable {
    @NonNull
    @Id
    @GeneratedValue
    private String id;

    @NonNull
    private String farmId;

    @NonNull
    private double area;
}

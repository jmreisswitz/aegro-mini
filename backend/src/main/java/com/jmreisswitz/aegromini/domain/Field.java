package com.jmreisswitz.aegromini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class Field extends ValidatorBaseClass implements Serializable {

    private String id;

    @NonNull
    private String farmId;

    @NonNull @NotEmpty @NotBlank
    private String name;

    @NonNull @Positive
    private double area;
}

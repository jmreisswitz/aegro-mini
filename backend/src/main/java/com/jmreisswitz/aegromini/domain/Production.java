package com.jmreisswitz.aegromini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Production extends ValidatorBaseClass implements Serializable {
    String id;

    @NonNull
    String fieldId;

    @NonNull @NotEmpty @NotBlank
    String productionType;

    @Positive
    Double productionAmount;
}

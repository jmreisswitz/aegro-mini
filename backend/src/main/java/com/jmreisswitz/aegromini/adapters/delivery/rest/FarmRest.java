package com.jmreisswitz.aegromini.adapters.delivery.rest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmRest implements Serializable {
    private String id;

    @NonNull @NotBlank @NotEmpty
    private String name;
}

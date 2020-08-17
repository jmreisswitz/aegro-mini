package com.jmreisswitz.aegromini.adapters.delivery.rest;


import com.jmreisswitz.aegromini.domain.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmRest implements Serializable {
    private String id;
    @NonNull
    @NotBlank
    private String name;
    private List<Field> fields;
}

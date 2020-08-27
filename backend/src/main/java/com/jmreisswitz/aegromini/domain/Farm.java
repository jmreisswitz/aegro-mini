package com.jmreisswitz.aegromini.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class Farm extends ValidatorBaseClass implements Serializable {

    private String id;

    @NonNull @NotEmpty @NotBlank
    private String name;
}

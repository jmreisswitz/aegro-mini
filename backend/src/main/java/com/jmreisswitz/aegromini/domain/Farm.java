package com.jmreisswitz.aegromini.domain;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class Farm {
    @NonNull String id;
    @NonNull private Set<Field> productions;
}

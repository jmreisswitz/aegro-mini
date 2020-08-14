package com.jmreisswitz.aegromini.domain;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class Field {
    @NonNull String farmId;
    @NonNull String id;
    @NonNull private boolean area;
    @NonNull private Set<Production> productions;
}

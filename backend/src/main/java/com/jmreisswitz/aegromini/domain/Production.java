package com.jmreisswitz.aegromini.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class Production {
    @NonNull String fieldId;
    @NonNull String id;
}

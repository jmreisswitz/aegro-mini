package com.jmreisswitz.aegromini.domain;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class Farm implements Serializable {
    @NonNull String id;
    @NonNull private HashMap<String, Field> fields;
}

package com.jmreisswitz.aegromini.adapters.delivery.rest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmRest implements Serializable {
    @NonNull
    private String id;
}

package com.jmreisswitz.aegromini.adapters.delivery.controllers;


import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FarmRest;
import com.jmreisswitz.aegromini.domain.Farm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FarmController {
    private final RestConverter<FarmRest, Farm> restConverter;
}

package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductionRest;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.usecases.production.AddProductionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("aegro_mini/production")
public class ProductionController {

    private final RestConverter<ProductionRest, Production> restConverter;
    private AddProductionUseCase addProductionUseCase;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public RestResponse<ProductionRest> save(@Valid @RequestBody ProductionRest productionRest){
        Production production = restConverter.mapToDomain(productionRest);
        ProductionRest productionRestResponse = restConverter.mapToRest(addProductionUseCase.execute(production));
        return new RestResponse<>(HttpStatus.CREATED, "Production created with success", productionRestResponse);
    }

}

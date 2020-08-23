package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductionRest;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.usecases.production.AddProductionUseCase;
import com.jmreisswitz.aegromini.usecases.production.DeleteProductionUseCase;
import com.jmreisswitz.aegromini.usecases.production.GetProductionByFieldIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("aegro_mini/production")
public class ProductionController {

    private final RestConverter<ProductionRest, Production> restConverter;
    private final AddProductionUseCase addProductionUseCase;
    private final GetProductionByFieldIdUseCase getProductionByFieldIdUseCase;
    private final DeleteProductionUseCase deleteProductionUseCase;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public RestResponse<ProductionRest> save(@Valid @RequestBody ProductionRest productionRest){
        Production production = restConverter.mapToDomain(productionRest);
        ProductionRest productionRestResponse = restConverter.mapToRest(addProductionUseCase.execute(production));
        return new RestResponse<>(HttpStatus.CREATED, "Production created with success", productionRestResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/field_id/{fieldId}")
    public RestResponse<List<ProductionRest>> getByFieldId(@PathVariable String fieldId){
        List<Production> productionList = getProductionByFieldIdUseCase.execute(fieldId);
        List<ProductionRest> productionRestList = new LinkedList<>();
        for (Production production : productionList){
            productionRestList.add(restConverter.mapToRest(production));
        }
        return new RestResponse<>(HttpStatus.OK, null, productionRestList);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{productionId}")
    public RestResponse<Void> delete(@PathVariable String productionId){
        deleteProductionUseCase.execute(productionId);
        return new RestResponse<>(HttpStatus.OK, "Production deleted");
    }
}
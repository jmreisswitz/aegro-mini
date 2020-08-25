package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductivityRest;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.domain.Productivity;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetProductivityByFieldUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("aegro_mini/productivity")
public class ProductivityController {
    private final RestConverter<ProductivityRest, Productivity> productivityRestConverter;
    private final GetProductivityByFieldUseCase getProductivityByFieldUseCase;
    private final GetFieldByIdUseCase getFieldByIdUseCase;


    @GetMapping("/productivity/{fieldId}")
    public RestResponse<List<ProductivityRest>> getProductivity(@PathVariable String fieldId)
            throws FieldNotFoundException {
        Optional<Field> field = getFieldByIdUseCase.execute(fieldId);
        List<Productivity> productivityList = getProductivityByFieldUseCase.execute(field.get());
        List<ProductivityRest> productivityRestList = new LinkedList<>();
        for (Productivity productivity: productivityList){
            productivityRestList.add(productivityRestConverter.mapToRest(productivity));
        }
        return new RestResponse<>(HttpStatus.OK, null, productivityRestList);
    }
}


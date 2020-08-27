package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductivityRest;
import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.domain.Productivity;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmHasNoFieldsException;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.farm.GetFarmByIdUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import com.jmreisswitz.aegromini.usecases.productivity.GetProductivityByFarmUseCase;
import com.jmreisswitz.aegromini.usecases.productivity.GetProductivityByFieldUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("aegro_mini/productivity")
public class ProductivityController {
    private final RestConverter<ProductivityRest, Productivity> productivityRestConverter;
    private final GetProductivityByFieldUseCase getProductivityByFieldUseCase;
    private final GetProductivityByFarmUseCase getProductivityByFarmUseCase;
    private final GetFieldByIdUseCase getFieldByIdUseCase;
    private final GetFarmByIdUseCase getFarmByIdUseCase;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/field_id/{fieldId}")
    public RestResponse<List<ProductivityRest>> getProductivityByFieldId(@PathVariable String fieldId)
            throws FieldNotFoundException {
        Optional<Field> field = getFieldByIdUseCase.execute(fieldId);
        List<Productivity> productivityList = getProductivityByFieldUseCase.execute(field.get());
        List<ProductivityRest> productivityRestList = productivityList.stream()
                .map(productivityRestConverter::mapToRest).collect(Collectors.toList());
        return new RestResponse<>(HttpStatus.OK, null, productivityRestList);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/farm_id/{farmId}")
    public RestResponse<List<ProductivityRest>> getProductivityByFarmId(@PathVariable String farmId)
            throws FarmHasNoFieldsException {
        Optional<Farm> farm = getFarmByIdUseCase.execute(farmId);
        List<Productivity> productivityList = getProductivityByFarmUseCase.execute(farm.get());
        List<ProductivityRest> productivityRestList = productivityList.stream()
                .map(productivityRestConverter::mapToRest).collect(Collectors.toList());
        return new RestResponse<>(HttpStatus.OK, null, productivityRestList);
    }
}


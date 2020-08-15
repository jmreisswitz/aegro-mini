package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.usecases.field.AddFieldUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("aegro_mini/field")
public class FieldController {
    private final AddFieldUseCase addFieldUseCase;
    private final GetFieldByIdUseCase getFieldByIdUseCase;
    private final RestConverter<FieldRest, Field> restConverter;

    @PostMapping
    public RestResponse<FieldRest> save(@Valid @RequestBody FieldRest fieldRest, HttpServletResponse response){
        Field field = restConverter.mapToDomain(fieldRest);
        FieldRest fieldRestSaved = restConverter.mapToRest(addFieldUseCase.execute(field));
        return new RestResponse<>(HttpStatus.CREATED, "Field created with success", fieldRestSaved);
    }

    @GetMapping("/{id}")
    public RestResponse<FieldRest> getById(@PathVariable String id){
        // TODO handle not found ids
        Optional<Field> field = getFieldByIdUseCase.execute(id);
        FieldRest fieldRest = restConverter.mapToRest(field.orElse(null));
        return new RestResponse<>(HttpStatus.OK, null, fieldRest);
    }
}

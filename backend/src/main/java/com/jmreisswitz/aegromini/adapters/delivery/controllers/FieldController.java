package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.usecases.field.AddFieldUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("aegro_mini/field")
public class FieldController {
    private final AddFieldUseCase addFieldUseCase;
    private final RestConverter<FieldRest, Field> restConverter;

    @PostMapping
    public RestResponse<FieldRest> save(@Valid @RequestBody FieldRest fieldRest, HttpServletResponse response){
        Field field = restConverter.mapToDomain(fieldRest);
        FieldRest fieldRestSaved = restConverter.mapToRest(addFieldUseCase.execute(field));
        return new RestResponse<>(HttpStatus.CREATED, "Field created with success", fieldRestSaved);
    }
}

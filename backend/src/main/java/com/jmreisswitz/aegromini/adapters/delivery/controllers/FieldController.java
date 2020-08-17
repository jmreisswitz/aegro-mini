package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.AddFieldUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("aegro_mini/field")
public class FieldController {
    private final AddFieldUseCase addFieldUseCase;
    private final GetFieldByIdUseCase getFieldByIdUseCase;
    private final GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;
    private final RestConverter<FieldRest, Field> restConverter;

    @PostMapping
    public RestResponse<FieldRest> save(@Valid @RequestBody FieldRest fieldRest){
        Field field = restConverter.mapToDomain(fieldRest);
        FieldRest fieldRestSaved = restConverter.mapToRest(addFieldUseCase.execute(field));
        return new RestResponse<>(HttpStatus.CREATED, "Field created with success", fieldRestSaved);
    }

    @GetMapping("/{id}")
    public RestResponse<FieldRest> getById(@PathVariable String id) throws FieldNotFoundException {
        Optional<Field> field = getFieldByIdUseCase.execute(id);
        if (field.isEmpty()){
            return new RestResponse<>(HttpStatus.NOT_FOUND, "Could not find fild with id " + id);
        }
        FieldRest fieldRest = restConverter.mapToRest(field.orElse(null));
        return new RestResponse<>(HttpStatus.OK, null, fieldRest);
    }

    @GetMapping("/byfarm/{farmId}")
    public RestResponse<List<FieldRest>> getByFarmId(@PathVariable String farmId){
        List<Field> fields = getFieldsByFarmIdUseCase.execute(farmId);
        if (fields.isEmpty()){
            return new RestResponse<>(HttpStatus.NOT_FOUND, "Could not found fields for farm with id " + farmId);
        }
        List<FieldRest> fieldsRest = new LinkedList<>();
        for (Field field : fields){
            fieldsRest.add(restConverter.mapToRest(field));
        }
        return new RestResponse<>(HttpStatus.OK, null, fieldsRest);
    }

}

package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FarmRest;
import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.usecases.AddFarmUseCase;
import com.jmreisswitz.aegromini.usecases.GetFarmByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("aegro_mini/farm")
public class FarmController {
    private final AddFarmUseCase addFarmUseCase;
    private final GetFarmByIdUseCase getFarmByIdUseCase;
    private final RestConverter<FarmRest, Farm> restConverter;

    @PostMapping
    public RestResponse<FarmRest> save(@Valid @RequestBody FarmRest farmRest, HttpServletResponse response){
        Farm farm = restConverter.mapToDomain(farmRest);
        FarmRest savedRestFarm = restConverter.mapToRest(addFarmUseCase.execute(farm));
        return new RestResponse<>(HttpStatus.CREATED, "Farm created with success", savedRestFarm);
    }

    @GetMapping(value = "/{id}")
    public RestResponse<FarmRest> getById(@PathVariable String id) {
        Optional<Farm> farm = getFarmByIdUseCase.execute(id);
        FarmRest farmRest = restConverter.mapToRest(farm.orElse(null));
        return new RestResponse<>(HttpStatus.OK, null, farmRest);
    }

}


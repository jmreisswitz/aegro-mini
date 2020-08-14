package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FarmRest;
import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.usecases.AddFarmUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class FarmController {
    private final AddFarmUseCase addFarmUseCase;
    private final RestConverter<FarmRest, Farm> restConverter;

    @PostMapping
    public RestResponse<FarmRest> save(@Valid @RequestBody FarmRest farmRest, HttpServletResponse response){
        Farm farm = restConverter.mapToDomain(farmRest);
        FarmRest savedRestFarm = restConverter.mapToRest(addFarmUseCase.execute(farm));
        return new RestResponse<>(HttpStatus.CREATED, "Farm created with success", savedRestFarm);
    }

}

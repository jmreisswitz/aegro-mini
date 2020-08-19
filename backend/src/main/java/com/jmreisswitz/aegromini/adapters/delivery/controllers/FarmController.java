package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.response.RestResponse;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FarmRest;
import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.usecases.farm.AddFarmUseCase;
import com.jmreisswitz.aegromini.usecases.farm.DeleteFarmByIdUseCase;
import com.jmreisswitz.aegromini.usecases.farm.GetAllFarmsUseCase;
import com.jmreisswitz.aegromini.usecases.farm.GetFarmByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("aegro_mini/farm")
public class FarmController {
    private final AddFarmUseCase addFarmUseCase;
    private final GetFarmByIdUseCase getFarmByIdUseCase;
    private final DeleteFarmByIdUseCase deleteFarmByIdUseCase;
    private final GetAllFarmsUseCase getAllFarmsUseCase;
    private final RestConverter<FarmRest, Farm> restConverter;

    @PostMapping
    public RestResponse<FarmRest> save(@Valid @RequestBody FarmRest farmRest){
        Farm farm = restConverter.mapToDomain(farmRest);
        FarmRest savedRestFarm = restConverter.mapToRest(addFarmUseCase.execute(farm));
        return new RestResponse<>(HttpStatus.CREATED, "Farm created with success", savedRestFarm);
    }

    @GetMapping("/farms")
    public RestResponse<List<FarmRest>> getAll() {
        List<Farm> farmList = getAllFarmsUseCase.execute();
        LinkedList<FarmRest> farmRestList = new LinkedList<>();
        for (Farm farm : farmList) {
            farmRestList.add(restConverter.mapToRest(farm));
        }
        return new RestResponse<>(HttpStatus.OK, null, farmRestList);
    }


    @GetMapping("/{id}")
    public RestResponse<FarmRest> getById(@PathVariable String id) {
        Optional<Farm> farm = getFarmByIdUseCase.execute(id);
        FarmRest farmRest = restConverter.mapToRest(farm.orElse(null));
        return new RestResponse<>(HttpStatus.OK, null, farmRest);
    }

    @DeleteMapping("/{id}")
    public RestResponse<Void> delete(@PathVariable String id){
        deleteFarmByIdUseCase.execute(id);
        return new RestResponse<>(HttpStatus.OK, "Farm removed with success.");
    }
}


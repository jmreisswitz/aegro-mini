package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GetFarmByIdUseCase {

    private final FarmRepository farmRepository;

    public Optional<Farm> execute(String farmId){
        if (farmId.isBlank()) {
            throw new IllegalArgumentException("Farm Id cannot be blank.");
        }
        Optional<Farm> farm = farmRepository.findOneById(farmId);
        if (farm.isEmpty()) {
            System.out.println("throw new FarmNotFoundException();");
        }
        return farm;
    }
}

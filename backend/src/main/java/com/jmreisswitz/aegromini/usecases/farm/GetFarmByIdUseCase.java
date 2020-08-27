package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GetFarmByIdUseCase {

    private final FarmRepository farmRepository;

    public Optional<Farm> execute(String farmId){
        Optional<Farm> farm = farmRepository.findOneById(farmId);
        if (farm.isEmpty()) {
            throw new FarmNotFoundException("Cant find farm with id " + farmId);
        }
        return farm;
    }
}

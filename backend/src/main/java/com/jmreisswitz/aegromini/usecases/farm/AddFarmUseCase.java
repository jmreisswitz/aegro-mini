package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;

public class AddFarmUseCase {
    private final FarmRepository farmRepository;

    public AddFarmUseCase(FarmRepository farmRepository){
        this.farmRepository = farmRepository;
    }

    public Farm execute(Farm farm){
        if (farm == null){
            throw new IllegalArgumentException("Farm cannot be null");
        }
        farm.checkIfValid();
        return this.farmRepository.save(farm);
    }
}

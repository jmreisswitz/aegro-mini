package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;

public class AddFarmUseCase {
    private final FarmRepository farmRepository;

    public AddFarmUseCase(FarmRepository farmRepository){
        this.farmRepository = farmRepository;
    }

    public Farm execute(Farm farm){
        return this.farmRepository.save(farm);
    }
}

package com.jmreisswitz.aegromini.usecases;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;

public class AddFarmUseCase {
    private final FarmRepository farmRepository;

    public AddFarmUseCase(FarmRepository farmRepository){
        this.farmRepository = farmRepository;
    }

    public void execute(Farm farm){
        this.farmRepository.save(farm);
    }
}

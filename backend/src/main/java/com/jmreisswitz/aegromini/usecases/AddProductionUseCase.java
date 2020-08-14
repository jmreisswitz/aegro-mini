package com.jmreisswitz.aegromini.usecases;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.NonNull;

public class AddProductionUseCase {
    private final ProductionRepository productionRepository;

    public AddProductionUseCase(@NonNull ProductionRepository productionRepository){
        this.productionRepository = productionRepository;
    }

    public void execute(Production production){
        this.productionRepository.save(production);
    }
}

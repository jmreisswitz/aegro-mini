package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddProductionUseCase {
    private final ProductionRepository productionRepository;

    public Production execute(Production production){
        return this.productionRepository.save(production);
    }
}

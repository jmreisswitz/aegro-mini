package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddProductionUseCase {
    private final ProductionRepository productionRepository;

    public void execute(Production production){
        this.productionRepository.save(production);
    }
}

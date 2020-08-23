package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProductionUseCase {

    private final ProductionRepository productionRepository;

    public void execute(String productionId){
        productionRepository.delete(productionId);
    }
}

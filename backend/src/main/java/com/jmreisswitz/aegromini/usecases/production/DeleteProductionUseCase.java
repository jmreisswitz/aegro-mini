package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProductionUseCase {

    private final ProductionRepository productionRepository;
    private final GetProductionByIdUseCase getProductionByIdUseCase;

    public void execute(String productionId) throws ProductionNotFoundException {
        getProductionByIdUseCase.execute(productionId);
        productionRepository.delete(productionId);
    }
}

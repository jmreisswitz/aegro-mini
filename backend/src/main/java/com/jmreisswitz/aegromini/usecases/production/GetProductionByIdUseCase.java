package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GetProductionByIdUseCase {
    private ProductionRepository productionRepository;

    public Production execute(String productionId) throws ProductionNotFoundException {
        Optional<Production> production = productionRepository.findOneById(productionId);
        if (production.isEmpty()){
            throw new ProductionNotFoundException("Could not found production with id " + productionId);
        }
        return production.get();
    }
}

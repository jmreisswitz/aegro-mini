package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetProductionByFarmIdUseCase {

    private final ProductionRepository productionRepository;

    public List<Production> execute(String farmId) {
        return null;
    }

}

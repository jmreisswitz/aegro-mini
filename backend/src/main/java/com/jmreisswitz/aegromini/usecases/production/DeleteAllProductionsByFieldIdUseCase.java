package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DeleteAllProductionsByFieldIdUseCase {

    public final GetProductionByFieldIdUseCase getProductionByFieldIdUseCase;
    public final DeleteProductionUseCase deleteProductionUseCase;

    public void execute(String fieldId) throws ProductionNotFoundException {
        List<Production> productionList = getProductionByFieldIdUseCase.execute(fieldId);
        for (Production production : productionList) {
            deleteProductionUseCase.execute(production.getId());
        }
    }
}

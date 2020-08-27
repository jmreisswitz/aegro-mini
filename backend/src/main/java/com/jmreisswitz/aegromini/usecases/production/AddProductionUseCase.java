package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddProductionUseCase {
    private final ProductionRepository productionRepository;
    private final GetFieldByIdUseCase getFieldByIdUseCase;

    public Production execute(Production production) throws FieldNotFoundException {
        if (production == null) {
            throw new IllegalArgumentException("production cannot be null");
        }
        production.checkIfValid();
        getFieldByIdUseCase.execute(production.getFieldId());
        return this.productionRepository.save(production);
    }
}

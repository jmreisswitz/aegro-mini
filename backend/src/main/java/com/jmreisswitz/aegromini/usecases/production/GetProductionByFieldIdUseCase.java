package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.adapters.persistence.converters.EntityConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.ProductionEntity;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetProductionByFieldIdUseCase {
    private final ProductionRepository productionRepository;
    private final EntityConverter<ProductionEntity, Production> entityConverter;

    public List<Production> execute(String fieldId){
        return productionRepository.listAllByFieldId(fieldId);
    }
}

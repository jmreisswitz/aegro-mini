package com.jmreisswitz.aegromini.adapters.persistence.converters;

import com.jmreisswitz.aegromini.adapters.persistence.entities.ProductionEntity;
import com.jmreisswitz.aegromini.domain.Production;

public class ProductionRepositoryConverter implements EntityConverter<ProductionEntity, Production> {
    @Override
    public ProductionEntity mapToEntity(Production domainObject) {
        return new ProductionEntity(
                domainObject.getId(),
                domainObject.getFieldId(),
                domainObject.getProductionType(),
                domainObject.getProductionAmount()
        );
    }

    @Override
    public Production mapToDomain(ProductionEntity entityObject) {
        return new Production(
                entityObject.getId(),
                entityObject.getFieldId(),
                entityObject.getProductionType(),
                entityObject.getProductionAmount()
        );
    }
}

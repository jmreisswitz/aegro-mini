package com.jmreisswitz.aegromini.adapters.delivery.converters;

import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductionRest;
import com.jmreisswitz.aegromini.domain.Production;

public class ProductionRestConverter implements RestConverter<ProductionRest, Production> {
    @Override
    public ProductionRest mapToRest(Production domainObject) {
        return new ProductionRest(
                domainObject.getId(),
                domainObject.getFieldId(),
                domainObject.getProductionType(),
                domainObject.getProductionAmount()
        );
    }

    @Override
    public Production mapToDomain(ProductionRest restObject) {
        return new Production(
                restObject.getId(),
                restObject.getFieldId(),
                restObject.getProductionType(),
                restObject.getProductionAmount()
        );
    }
}

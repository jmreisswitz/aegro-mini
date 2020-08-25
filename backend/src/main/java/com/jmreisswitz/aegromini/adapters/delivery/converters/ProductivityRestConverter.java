package com.jmreisswitz.aegromini.adapters.delivery.converters;

import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductivityRest;
import com.jmreisswitz.aegromini.domain.Productivity;

public class ProductivityRestConverter implements RestConverter<ProductivityRest, Productivity> {
    @Override
    public ProductivityRest mapToRest(Productivity domainObject) {
        return new ProductivityRest(domainObject.getProductionType(), domainObject.getProductivityAmount());
    }

    @Override
    public Productivity mapToDomain(ProductivityRest restObject) {
        return new Productivity(restObject.getProductionType(), restObject.getProductivityAmount());
    }
}

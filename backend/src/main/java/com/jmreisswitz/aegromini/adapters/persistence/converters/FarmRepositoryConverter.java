package com.jmreisswitz.aegromini.adapters.persistence.converters;

import com.jmreisswitz.aegromini.adapters.persistence.entities.FarmEntity;
import com.jmreisswitz.aegromini.domain.Farm;

public class FarmRepositoryConverter implements EntityConverter<FarmEntity, Farm> {
    @Override
    public FarmEntity mapToEntity(Farm domainObject) {
        return new FarmEntity(domainObject.getId(), domainObject.getName(), domainObject.getFields());
    }

    @Override
    public Farm mapToDomain(FarmEntity entityObject) {
        return new Farm(entityObject.getId(), entityObject.getName(), entityObject.getFields());
    }
}

package com.jmreisswitz.aegromini.adapters.persistence.converters;

import com.jmreisswitz.aegromini.adapters.persistence.entities.FieldEntity;
import com.jmreisswitz.aegromini.domain.Field;

import java.util.HashMap;

public class FieldRepositoryConverter implements EntityConverter<FieldEntity, Field> {
    @Override
    public FieldEntity mapToEntity(Field domainObject) {
        return new FieldEntity(
                domainObject.getName(),
                domainObject.getId(),
                domainObject.getFarmId(),
                domainObject.getArea()
        );
    }

    @Override
    public Field mapToDomain(FieldEntity entityObject) {
        return new Field(
                entityObject.getName(),
                entityObject.getFarmId(),
                entityObject.getId(),
                entityObject.getArea(),
                new HashMap<>()
        );
    }
}

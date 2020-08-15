package com.jmreisswitz.aegromini.adapters.delivery.converters;

import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.domain.Field;

import java.util.HashMap;

public class FieldRestConverter implements RestConverter<FieldRest, Field>{
    @Override
    public FieldRest mapToRest(Field domainObject) {
        return new FieldRest(
                domainObject.getId(),
                domainObject.getName(),
                domainObject.getFarmId(),
                domainObject.getArea()
        );
    }

    @Override
    public Field mapToDomain(FieldRest restObject) {
        return new Field(
                restObject.getName(),
                restObject.getId(),
                restObject.getFarmId(),
                restObject.getArea(),
                new HashMap<>()
        );
    }
}

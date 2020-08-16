package com.jmreisswitz.aegromini.adapters.delivery.converters;

import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.domain.Field;

public class FieldRestConverter implements RestConverter<FieldRest, Field>{
    @Override
    public FieldRest mapToRest(Field domainObject) {
        return new FieldRest(
                domainObject.getId(),
                domainObject.getFarmId(),
                domainObject.getName(),
                domainObject.getArea()
        );
    }

    @Override
    public Field mapToDomain(FieldRest restObject) {
        return new Field(
                restObject.getId(),
                restObject.getFarmId(),
                restObject.getName(),
                restObject.getArea()
        );
    }
}

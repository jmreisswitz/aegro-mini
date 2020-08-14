package com.jmreisswitz.aegromini.adapters.persistence.converters;

import java.io.Serializable;

public interface EntityConverter<T extends Serializable, P extends Serializable> {
    default T mapToEntity(final P domainObject){
        throw new UnsupportedOperationException();
    }

    default P mapToDomain(final T entityObject){
        throw new UnsupportedOperationException();
    }

}

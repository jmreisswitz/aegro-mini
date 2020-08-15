package com.jmreisswitz.aegromini.adapters.delivery.converters;

import com.jmreisswitz.aegromini.adapters.delivery.rest.FarmRest;
import com.jmreisswitz.aegromini.domain.Farm;

import java.util.HashMap;

public class FarmRestConverter implements RestConverter<FarmRest, Farm> {
    @Override
    public FarmRest mapToRest(Farm domainObject) {
        return new FarmRest(domainObject.getId(), domainObject.getName());
    }

    @Override
    public Farm mapToDomain(FarmRest restObject) {
        return new Farm(restObject.getId(), restObject.getName(), new HashMap<>());
    }
}

package com.jmreisswitz.aegromini.usecases.field;


import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetFieldsByFarmIdUseCase {
    private final FieldRepository fieldRepository;

    public List<Field> execute(String farmId){
        return fieldRepository.listAllByFarmId(farmId);
    }
}

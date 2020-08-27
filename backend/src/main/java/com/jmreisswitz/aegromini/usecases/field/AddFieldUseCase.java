package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import com.jmreisswitz.aegromini.usecases.farm.GetFarmByIdUseCase;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AddFieldUseCase {
    private final FieldRepository fieldRepository;
    private final GetFarmByIdUseCase getFarmByIdUseCase;


    public Field execute(Field field){
        if (field == null){
            throw new IllegalArgumentException("field is null");
        }
        field.checkIfValid();
        getFarmByIdUseCase.execute(field.getFarmId());
        return fieldRepository.save(field);
    }
}

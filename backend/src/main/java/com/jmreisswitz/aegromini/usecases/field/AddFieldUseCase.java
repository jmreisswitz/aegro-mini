package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddFieldUseCase {
    private final FieldRepository fieldRepository;

    public Field execute(Field field){
        return fieldRepository.save(field);
    }
}

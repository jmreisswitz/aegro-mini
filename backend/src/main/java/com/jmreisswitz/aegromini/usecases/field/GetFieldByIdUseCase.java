package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GetFieldByIdUseCase {

    public final FieldRepository fieldRepository;

    public Optional<Field> execute(String fieldId) throws FieldNotFoundException {
        Optional<Field> field = fieldRepository.findOneById(fieldId);
        if (field.isEmpty()){
            throw new FieldNotFoundException("Cant find field with id " + fieldId);
        }
        return field;
    }

}

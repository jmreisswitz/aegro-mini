package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GetFieldByIdUseCase {

    public final FieldRepository fieldRepository;

    public Optional<Field> execute(String fieldId){
        return fieldRepository.findOneById(fieldId);
    }

}

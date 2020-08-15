package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import lombok.NonNull;

public class AddFieldUseCase {
    private final FieldRepository fieldRepository;

    public AddFieldUseCase(@NonNull FieldRepository fieldRepository){
        this.fieldRepository = fieldRepository;
    }

    public void execute(Field field){
        this.fieldRepository.save(field);
    }
}

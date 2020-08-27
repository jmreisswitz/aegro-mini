package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import com.jmreisswitz.aegromini.usecases.production.DeleteAllProductionsByFieldIdUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DeleteFieldByIdUseCase {

    private final FieldRepository fieldRepository;
    private final DeleteAllProductionsByFieldIdUseCase deleteAllProductionsByFieldIdUseCase;

    public void execute(String fieldId) throws FieldNotFoundException, ProductionNotFoundException {
        Optional<Field> field = fieldRepository.findOneById(fieldId);
        if (field.isEmpty()){
            throw new FieldNotFoundException("Cant find field with id " + fieldId);
        }
        deleteAllProductionsByFieldIdUseCase.execute(fieldId);
        fieldRepository.delete(fieldId);
    }

}

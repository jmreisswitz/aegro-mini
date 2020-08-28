package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DeleteAllFieldsByFarmIdUseCase {

    private final GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;
    private final DeleteFieldByIdUseCase deleteFieldByIdUseCase;

    public void execute(String farmId) throws FieldNotFoundException, ProductionNotFoundException {
        List<Field> fieldList = getFieldsByFarmIdUseCase.execute(farmId);
        for (Field field : fieldList){
            deleteFieldByIdUseCase.execute(field.getId());
        }
    }
}

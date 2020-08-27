package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DeleteAllFieldsByFarmIdUseCase {

    public final GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;
    public final DeleteFieldByIdUseCase deleteFieldByIdUseCase;

    public void execute(String farmId) throws FieldNotFoundException {
        List<Field> fieldList = getFieldsByFarmIdUseCase.execute(farmId);
        for (Field field : fieldList){
            deleteFieldByIdUseCase.execute(field.getId());
        }
    }
}

package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.domain.Productivity;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmHasNoFieldsException;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
import com.jmreisswitz.aegromini.usecases.production.GetProductionByFieldIdUseCase;
import com.jmreisswitz.aegromini.usecases.productivity.CalculateProductivityUseCase;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class GetProductivityByFarmUseCase {

    private final CalculateProductivityUseCase calculateProductivityUseCase;
    private final GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;
    private final GetProductionByFieldIdUseCase getProductionByFieldIdUseCase;

    public List<Productivity> execute(Farm farm) throws FarmHasNoFieldsException {
        List<Field> fieldList = getFieldsByFarmIdUseCase.execute(farm.getId());
        if (fieldList.isEmpty()){
            throw new FarmHasNoFieldsException("Farm " + farm + " has no fields.");
        }
        double farmTotalArea = fieldList.stream().map(Field::getArea).reduce(0.0, Double::sum);
        List<Production> productionList = getProductionListFromFields(fieldList);
        return calculateProductivityUseCase.execute(productionList, farmTotalArea);
    }

    private List<Production> getProductionListFromFields(List<Field> fieldList) {
        List<Production> productionList = new LinkedList<>();
        for (Field field : fieldList) {
            productionList.addAll(getProductionByFieldIdUseCase.execute(field.getId()));
        }
        return productionList;
    }

}

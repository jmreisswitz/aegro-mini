package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.domain.Productivity;
import com.jmreisswitz.aegromini.usecases.production.CalculateProductivityUseCase;
import com.jmreisswitz.aegromini.usecases.production.GetProductionByFieldIdUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetProductivityUseCase {
    private final GetProductionByFieldIdUseCase getProductionByFieldIdUseCase;
    private final CalculateProductivityUseCase calculateProductivityUseCase;

    public List<Productivity> execute(Field field){
        List<Production> productionList = getProductionByFieldIdUseCase.execute(field.getId());
        return calculateProductivityUseCase(productionList, field.getArea());
    }
}

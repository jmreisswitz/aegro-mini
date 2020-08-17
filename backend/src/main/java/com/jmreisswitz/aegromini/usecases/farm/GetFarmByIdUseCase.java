package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GetFarmByIdUseCase {

    private final FarmRepository farmRepository;
    private final GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;

    public Optional<Farm> execute(String farmId){
        Optional<Farm> farm = farmRepository.findOneById(farmId);
        if (farm.isEmpty()) {
            throw new FarmNotFoundException("Cant find farm with id " + farmId);
        }
        List<Field> fields = getFieldsByFarmIdUseCase.execute(farmId);
        farm.ifPresent(f->f.setFields(fields));
        return farm;
    }
}

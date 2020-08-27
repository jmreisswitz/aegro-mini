package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.DeleteAllFieldsByFarmIdUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DeleteFarmByIdUseCase {
    private final FarmRepository farmRepository;
    private final DeleteAllFieldsByFarmIdUseCase deleteAllFieldsByFarmIdUseCase;

    public void execute(String farmId) throws FieldNotFoundException, ProductionNotFoundException {
        Optional<Farm> farmToDelete = farmRepository.findOneById(farmId);
        if (farmToDelete.isEmpty()){
            throw new FarmNotFoundException("Cant find farm with id " + farmId);
        }
        deleteAllFieldsByFarmIdUseCase.execute(farmId);
        farmRepository.delete(farmId);
    }

}

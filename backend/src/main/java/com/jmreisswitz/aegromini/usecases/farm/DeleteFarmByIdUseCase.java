package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DeleteFarmByIdUseCase {
    private final FarmRepository farmRepository;

    public void execute(String farmId) {
        Optional<Farm> farmToDelete = farmRepository.findOneById(farmId);
        if (farmToDelete.isEmpty()){
            throw new FarmNotFoundException("Cant find farm with id " + farmId);
        }

        farmRepository.delete(farmId);
    }

}

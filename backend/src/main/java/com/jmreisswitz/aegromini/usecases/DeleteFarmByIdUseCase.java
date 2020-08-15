package com.jmreisswitz.aegromini.usecases;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DeleteFarmByIdUseCase {
    private final FarmRepository farmRepository;

    public void execute(String farmId) {
        if (farmId.isBlank()){
            throw new IllegalArgumentException("Farm Id cannot be Blank.");
        }
        Optional<Farm> farmToDelete = farmRepository.findOneById(farmId);
        if (farmToDelete.isEmpty()){
            System.out.println("throw new FarmNotFoundException()");
        }
        farmRepository.delete(farmId);
    }

}

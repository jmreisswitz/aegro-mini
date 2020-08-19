package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class GetAllFarmsUseCase {

    private final FarmRepository farmRepository;

    public List<Farm> execute() {
        return farmRepository.findAll();
    }
}

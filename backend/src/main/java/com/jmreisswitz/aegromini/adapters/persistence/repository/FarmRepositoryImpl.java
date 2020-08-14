package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.converters.FarmRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.FarmEntity;
import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FarmRepositoryImpl implements FarmRepository {
    private final FarmCrudRepository farmCrudRepository;
    private final FarmRepositoryConverter farmRepositoryConverter;

    @Override
    public Farm save(Farm farm) {
        FarmEntity farmEntity = farmRepositoryConverter.mapToEntity(farm);
        FarmEntity savedEntity = farmCrudRepository.save(farmEntity);
        return this.farmRepositoryConverter.mapToDomain(savedEntity);
    }

    @Override
    public List<Farm> listAll() {
        List<FarmEntity> farmEntities = farmCrudRepository.findAll();
        return farmEntities.stream().map(farmRepositoryConverter::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Farm> findOneById(String id) {
        Optional<FarmEntity> foundFarm = farmCrudRepository.findOneById(id);
        if (foundFarm.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(farmRepositoryConverter.mapToDomain(foundFarm.get()));
    }

    @Override
    public void delete(String id) {
        farmCrudRepository.deleteById(id);
    }
}

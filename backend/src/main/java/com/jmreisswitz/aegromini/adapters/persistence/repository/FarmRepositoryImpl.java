package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.converters.FarmRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.FarmEntity;
import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FarmRepositoryImpl implements FarmRepository {
    private final FarmMongoRepository farmMongoRepository;
    private final FarmRepositoryConverter farmRepositoryConverter;

    public FarmRepositoryImpl(FarmMongoRepository farmMongoRepository, FarmRepositoryConverter farmRepositoryConverter) {
        this.farmMongoRepository = farmMongoRepository;
        this.farmRepositoryConverter = farmRepositoryConverter;
    }

    @Override
    public Farm save(Farm farm) {
        FarmEntity farmEntity = farmRepositoryConverter.mapToEntity(farm);
        FarmEntity savedEntity = farmMongoRepository.save(farmEntity);
        return this.farmRepositoryConverter.mapToDomain(savedEntity);
    }

    @Override
    public List<Farm> listAll() {
        List<FarmEntity> farmEntities = farmMongoRepository.findAll();
        return farmEntities.stream().map(farmRepositoryConverter::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Farm> findOneById(String id) {
        Optional<FarmEntity> foundFarm = farmMongoRepository.findOneById(id);
        if (foundFarm.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(farmRepositoryConverter.mapToDomain(foundFarm.get()));
    }

    @Override
    public void delete(String id) {
        farmMongoRepository.deleteById(id);
    }
}

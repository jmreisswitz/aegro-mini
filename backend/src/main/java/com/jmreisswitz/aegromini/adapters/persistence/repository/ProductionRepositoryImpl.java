package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.converters.EntityConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.ProductionEntity;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductionRepositoryImpl implements ProductionRepository {
    private final ProductionMongoRepository productionMongoRepository;
    private final EntityConverter<ProductionEntity, Production> entityConverter;

    @Override
    public Production save(Production production) {
        ProductionEntity productionEntity = productionMongoRepository.save(entityConverter.mapToEntity(production));
        return entityConverter.mapToDomain(productionEntity);
    }

    @Override
    public List<Production> listAll() {
        List<ProductionEntity> productionEntityList = productionMongoRepository.findAll();
        return productionEntityList.stream().map(entityConverter::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Production> listAllByFieldId(String fieldId) {
        List<ProductionEntity> productionEntityList = productionMongoRepository.getAllByFieldId(fieldId);
        return productionEntityList.stream().map(entityConverter::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Production> findOneById(String id) {
        Optional<ProductionEntity> productionEntity = productionMongoRepository.findById(id);
        if (productionEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entityConverter.mapToDomain(productionEntity.get()));
    }

    @Override
    public void delete(String id) {
        productionMongoRepository.deleteById(id);
    }
}

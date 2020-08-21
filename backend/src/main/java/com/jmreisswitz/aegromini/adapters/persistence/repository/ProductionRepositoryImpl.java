package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.converters.ProductionRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.ProductionEntity;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductionRepositoryImpl implements ProductionRepository {
    private final ProductionMongoRepository productionMongoRepository;
    private final ProductionRepositoryConverter entityConverter;

    @Override
    public Production save(Production production) {
        ProductionEntity productionEntity = productionMongoRepository.save(entityConverter.mapToEntity(production));
        return entityConverter.mapToDomain(productionEntity);
    }

    @Override
    public List<Production> listAll() {
        List<ProductionEntity> productionEntityList = productionMongoRepository.findAll();
        List<Production> productionList = new LinkedList<>();
        for (ProductionEntity productionEntity: productionEntityList){
            productionList.add(entityConverter.mapToDomain(productionEntity));
        }
        return productionList;
    }

    @Override
    public List<Production> listAllByFieldId(String fieldId) {
        List<ProductionEntity> productionEntityList = productionMongoRepository.getAllByFieldId(fieldId);
        List<Production> productionList = new LinkedList<>();
        for (ProductionEntity productionEntity: productionEntityList){
            productionList.add(entityConverter.mapToDomain(productionEntity));
        }
        return productionList;
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

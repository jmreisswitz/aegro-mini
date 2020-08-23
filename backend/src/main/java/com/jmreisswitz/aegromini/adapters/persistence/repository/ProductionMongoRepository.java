package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.entities.ProductionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionMongoRepository extends MongoRepository<ProductionEntity, String> {
    List<ProductionEntity> getAllByFieldId(String fieldId);
}

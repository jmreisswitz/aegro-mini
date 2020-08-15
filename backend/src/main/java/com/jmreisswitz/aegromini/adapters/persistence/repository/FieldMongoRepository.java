package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.entities.FieldEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldMongoRepository extends MongoRepository<FieldEntity, String> {
    List<FieldEntity> getByFarmId(String farmId);
}

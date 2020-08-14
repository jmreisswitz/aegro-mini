package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.entities.FarmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmCrudRepository extends CrudRepository<FarmEntity, String> {
    Optional<FarmEntity> findOneById(String id);
    List<FarmEntity> findAll();
}

package com.jmreisswitz.aegromini.ports.repository;

import com.jmreisswitz.aegromini.domain.Production;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ProductionRepository {
    Production save(Production production);

    List<Production> listAll();

    List<Production> listAllByFieldId(String fieldId);

    Optional<Production> findOneById(String id);

    void delete(String id);
}

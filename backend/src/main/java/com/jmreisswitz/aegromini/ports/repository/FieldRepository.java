package com.jmreisswitz.aegromini.ports.repository;

import com.jmreisswitz.aegromini.domain.Field;

import java.util.List;
import java.util.Optional;

public interface FieldRepository {
    Field save(Field field);

    List<Field> listAll();

    List<Field> listAllByFarmId(String farmId);

    Optional<Field> findOneById(String id);

    void delete(String id);

}

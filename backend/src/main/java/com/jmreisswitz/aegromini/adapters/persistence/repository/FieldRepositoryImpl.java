package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.converters.FieldRepositoryConverter;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class FieldRepositoryImpl implements FieldRepository {
    private final FieldMongoRepository fieldMongoRepository;
    private final FieldRepositoryConverter fieldRepositoryConverter;

    @Override
    public Field save(Field field) {
        return null;
    }

    @Override
    public List<Field> listAllByFarmId(String farmId) {
        return null;
    }

    @Override
    public Optional<Field> findOneById(String id) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }
}

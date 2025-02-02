package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.converters.FieldRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.FieldEntity;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
public class FieldRepositoryImpl implements FieldRepository {
    private final FieldMongoRepository fieldMongoRepository;
    private final FieldRepositoryConverter fieldRepositoryConverter;

    @Override
    public Field save(Field field) {
        FieldEntity fieldEntitySaved = fieldMongoRepository.save(fieldRepositoryConverter.mapToEntity(field));
        return fieldRepositoryConverter.mapToDomain(fieldEntitySaved);
    }

    @Override
    public List<Field> listAllByFarmId(String farmId) {
        List<FieldEntity> fieldEntityList = fieldMongoRepository.getByFarmId(farmId);
        return fieldEntityList.stream().map(fieldRepositoryConverter::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Field> findOneById(String fieldId) {
        Optional<FieldEntity> fieldFromRepository = fieldMongoRepository.findById(fieldId);
        if(fieldFromRepository.isEmpty())
            return Optional.empty();
        return fieldFromRepository.map(fieldRepositoryConverter::mapToDomain);
    }

    @Override
    public void delete(String fieldId) {
        fieldMongoRepository.deleteById(fieldId);
    }
}

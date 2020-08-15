package com.jmreisswitz.aegromini.adapters.persistence.repository;

import com.jmreisswitz.aegromini.adapters.persistence.converters.FieldRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.FieldEntity;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


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
        List<Field> fieldsList = new LinkedList<>();
        for (FieldEntity field : fieldEntityList){
            fieldsList.add(fieldRepositoryConverter.mapToDomain(field));
        }
        return fieldsList;
    }

    @Override
    public Optional<Field> findOneById(String fieldId) {
        if (fieldId.isBlank()){
            throw new UnsupportedOperationException("Id cannot be blank.");
        }
        Optional<FieldEntity> fieldFromRepository = fieldMongoRepository.findById(fieldId);
        if (fieldFromRepository.isPresent()) {
            return Optional.of(fieldRepositoryConverter.mapToDomain(fieldFromRepository.get()));
        }
        System.out.println("Empty string");
        return Optional.empty();
    }

    @Override
    public void delete(String fieldId) {
        if (fieldId.isBlank()){
            throw new UnsupportedOperationException("If cannot be blank");
        }
        Optional<FieldEntity> fieldFromRepository = fieldMongoRepository.findById(fieldId);
        if (fieldFromRepository.isEmpty()){
            System.out.println("Field oes not exists");
            return;
        }
        fieldMongoRepository.delete(fieldFromRepository.get());
    }
}

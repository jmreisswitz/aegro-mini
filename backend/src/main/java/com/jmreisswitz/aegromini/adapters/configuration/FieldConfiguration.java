package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.FieldRestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.adapters.persistence.converters.FieldRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FieldMongoRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FieldRepositoryImpl;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import com.jmreisswitz.aegromini.usecases.field.AddFieldUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FieldConfiguration {
    @Autowired
    private FieldMongoRepository fieldMongoRepository;

    @Bean
    FieldRepositoryConverter createFieldRepositoryConverter() {
        return new FieldRepositoryConverter();
    }

    @Bean
    FieldRepository createFieldRepository() {
        return new FieldRepositoryImpl(fieldMongoRepository, createFieldRepositoryConverter());
    }

    @Bean
    RestConverter<FieldRest, Field> createRestConverter() {
        return new FieldRestConverter();
    }

    @Bean
    AddFieldUseCase createAddFieldUseCase() {
        return new AddFieldUseCase(createFieldRepository());
    }

    @Bean
    GetFieldByIdUseCase createGetFieldByIdUseCase() { return new GetFieldByIdUseCase(createFieldRepository()); }

    @Bean
    GetFieldsByFarmIdUseCase createGetFieldsByFarmIdUseCase() {
        return new GetFieldsByFarmIdUseCase(createFieldRepository());
    }

}

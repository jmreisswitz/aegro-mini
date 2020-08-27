package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.FieldRestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.adapters.persistence.converters.FieldRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FieldMongoRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FieldRepositoryImpl;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import com.jmreisswitz.aegromini.usecases.farm.GetFarmByIdUseCase;
import com.jmreisswitz.aegromini.usecases.field.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class FieldConfiguration {
    private final FieldMongoRepository fieldMongoRepository;
    private final GetFarmByIdUseCase getFarmByIdUseCase;

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
        return new AddFieldUseCase(createFieldRepository(), getFarmByIdUseCase);
    }

    @Bean
    GetFieldByIdUseCase createGetFieldByIdUseCase() { return new GetFieldByIdUseCase(createFieldRepository()); }

    @Bean
    public GetFieldsByFarmIdUseCase createGetFieldsByFarmIdUseCase() {
        return new GetFieldsByFarmIdUseCase(createFieldRepository());
    }

    @Bean
    public DeleteFieldByIdUseCase createDeleteFieldByIdUseCase() {
        return new DeleteFieldByIdUseCase(createFieldRepository());
    }

    @Bean
    public DeleteAllFieldsByFarmIdUseCase createDeleteAllFieldsByFarmIdUseCase() {
        return new DeleteAllFieldsByFarmIdUseCase(createGetFieldsByFarmIdUseCase(), createDeleteFieldByIdUseCase());
    }
}

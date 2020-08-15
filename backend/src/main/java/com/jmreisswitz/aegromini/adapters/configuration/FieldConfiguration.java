package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.persistence.converters.FieldRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FieldMongoRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FieldRepositoryImpl;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
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

}

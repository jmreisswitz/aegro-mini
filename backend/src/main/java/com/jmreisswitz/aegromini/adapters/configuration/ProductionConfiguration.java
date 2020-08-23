package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.ProductionRestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductionRest;
import com.jmreisswitz.aegromini.adapters.persistence.converters.EntityConverter;
import com.jmreisswitz.aegromini.adapters.persistence.converters.ProductionRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.entities.ProductionEntity;
import com.jmreisswitz.aegromini.adapters.persistence.repository.ProductionMongoRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.ProductionRepositoryImpl;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import com.jmreisswitz.aegromini.usecases.production.AddProductionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductionConfiguration {
    @Autowired
    ProductionMongoRepository productionMongoRepository;

    @Bean
    RestConverter<ProductionRest, Production> restConverter() {
        return new ProductionRestConverter();
    }

    @Bean
    EntityConverter<ProductionEntity, Production> entityConverter() {
        return new ProductionRepositoryConverter();
    }

    @Bean
    ProductionRepository productionRepository() {
        return new ProductionRepositoryImpl(productionMongoRepository, entityConverter());
    }

    @Bean
    AddProductionUseCase addProductionUseCase() {
        return new AddProductionUseCase(productionRepository());
    }
}

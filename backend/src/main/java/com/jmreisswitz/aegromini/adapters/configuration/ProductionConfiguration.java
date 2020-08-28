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
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import com.jmreisswitz.aegromini.usecases.production.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class ProductionConfiguration {
    final ProductionMongoRepository productionMongoRepository;
    final GetFieldByIdUseCase getFieldByIdUseCase;

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
        return new AddProductionUseCase(productionRepository(), getFieldByIdUseCase);
    }

    @Bean
    GetProductionByIdUseCase getProductionByIdUseCase() {
        return new GetProductionByIdUseCase(productionRepository());
    }

    @Bean
    GetProductionByFieldIdUseCase getProductionByFieldIdUseCase() {
        return new GetProductionByFieldIdUseCase(productionRepository(), entityConverter());
    }

    @Bean
    DeleteProductionUseCase deleteProductionUseCase() {
        return new DeleteProductionUseCase(productionRepository(), getProductionByIdUseCase());
    }

    @Bean
    DeleteAllProductionsByFieldIdUseCase deleteAllProductionsByFieldIdUseCase() {
        return new DeleteAllProductionsByFieldIdUseCase(getProductionByFieldIdUseCase(), deleteProductionUseCase());
    }
}

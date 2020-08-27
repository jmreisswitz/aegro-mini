package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.ProductivityRestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.rest.ProductivityRest;
import com.jmreisswitz.aegromini.domain.Productivity;
import com.jmreisswitz.aegromini.usecases.productivity.GetProductivityByFarmUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
import com.jmreisswitz.aegromini.usecases.productivity.GetProductivityByFieldUseCase;
import com.jmreisswitz.aegromini.usecases.production.GetProductionByFieldIdUseCase;
import com.jmreisswitz.aegromini.usecases.productivity.CalculateProductivityUseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class ProductivityConfiguration {
    private final GetProductionByFieldIdUseCase getProductionByFieldIdUseCase;
    private final GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;

    @Bean
    RestConverter<ProductivityRest, Productivity> createProductivityRestConverter() {return new ProductivityRestConverter();}

    @Bean
    CalculateProductivityUseCase createCalculateProductivityUseCase() {return new CalculateProductivityUseCase();}

    @Bean
    GetProductivityByFieldUseCase createGetProductivityByFieldUseCase() {
        return new GetProductivityByFieldUseCase(getProductionByFieldIdUseCase, createCalculateProductivityUseCase());}

    @Bean
    public GetProductivityByFarmUseCase createGetProductivityByFarmUseCase() {
        return new GetProductivityByFarmUseCase(createCalculateProductivityUseCase(),
                getFieldsByFarmIdUseCase, getProductionByFieldIdUseCase);
    }
}

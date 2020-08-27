package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.FarmRestConverter;
import com.jmreisswitz.aegromini.adapters.persistence.converters.FarmRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmMongoRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmRepositoryImpl;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.farm.*;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class FarmConfiguration {
    private final FarmMongoRepository farmMongoRepository;

    @Bean
    public FarmRepositoryConverter createFarmRepositoryConverter(){
        return new FarmRepositoryConverter();
    }

    @Bean
    public FarmRepository createFarmRepository() {
        return new FarmRepositoryImpl(farmMongoRepository, createFarmRepositoryConverter());
    }

    @Bean
    public GetFarmByIdUseCase createGetFarmByIdUseCase() {
        return new GetFarmByIdUseCase(createFarmRepository());
    }

    @Bean
    public AddFarmUseCase createAddFarmUseCase() {
        return new AddFarmUseCase(createFarmRepository());
    }

    @Bean
    public DeleteFarmByIdUseCase createDeleteFarmByIdUseCase() { return new DeleteFarmByIdUseCase(createFarmRepository()); }

    @Bean
    public FarmRestConverter createFarmRestConverter() {
        return new FarmRestConverter();
    }

    @Bean
    public GetAllFarmsUseCase createGetAllFarmsUseCase() { return new GetAllFarmsUseCase(createFarmRepository());}
}

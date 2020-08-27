package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.FarmRestConverter;
import com.jmreisswitz.aegromini.adapters.persistence.converters.FarmRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmMongoRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmRepositoryImpl;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.farm.*;
import com.jmreisswitz.aegromini.usecases.field.DeleteAllFieldsByFarmIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class FarmConfiguration {
    @Autowired
    private FarmMongoRepository farmMongoRepository;

    @Autowired
    private DeleteAllFieldsByFarmIdUseCase deleteAllFieldsByFarmIdUseCase;

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

    @Lazy
    @Bean
    public DeleteFarmByIdUseCase createDeleteFarmByIdUseCase() {
        return new DeleteFarmByIdUseCase(createFarmRepository(), deleteAllFieldsByFarmIdUseCase); }

    @Bean
    public FarmRestConverter createFarmRestConverter() {
        return new FarmRestConverter();
    }

    @Bean
    public GetAllFarmsUseCase createGetAllFarmsUseCase() { return new GetAllFarmsUseCase(createFarmRepository());}
}

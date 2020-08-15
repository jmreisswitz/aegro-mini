package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.FarmRestConverter;
import com.jmreisswitz.aegromini.adapters.persistence.converters.FarmRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmMongoRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmRepositoryImpl;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.AddFarmUseCase;
import com.jmreisswitz.aegromini.usecases.GetFarmByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FarmConfiguration {
    @Autowired
    private FarmMongoRepository farmMongoRepository;

    @Bean
    public FarmRepositoryConverter createFarmRepositoryConverter(){
        return new FarmRepositoryConverter();
    }

    @Bean
    public FarmRepository createFarmRepository() {
        return new FarmRepositoryImpl(farmMongoRepository, createFarmRepositoryConverter());
    }

    @Bean
    public GetFarmByIdUseCase getFarmByIdUseCase() {return new GetFarmByIdUseCase(createFarmRepository());}

    @Bean
    public AddFarmUseCase createAddFarmUseCase() {
        return new AddFarmUseCase(createFarmRepository());
    }

    @Bean
    public FarmRestConverter createFarmRestConverter() {
        return new FarmRestConverter();
    }


}

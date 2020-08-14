package com.jmreisswitz.aegromini.adapters.configuration;

import com.jmreisswitz.aegromini.adapters.delivery.converters.FarmRestConverter;
import com.jmreisswitz.aegromini.adapters.persistence.converters.FarmRepositoryConverter;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmCrudRepository;
import com.jmreisswitz.aegromini.adapters.persistence.repository.FarmRepositoryImpl;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.AddFarmUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class FarmConfiguration {
    @Autowired
    private FarmCrudRepository farmCrudRepository;

    @Bean
    public FarmRepositoryConverter createFarmRepositoryConverter(){
        return new FarmRepositoryConverter();
    }

    @Bean
    public FarmRepository createPlanetRepository() {
        return new FarmRepositoryImpl(farmCrudRepository, createFarmRepositoryConverter());
    }

    @Bean
    public AddFarmUseCase createAddFarmUseCase() {
        return new AddFarmUseCase(createPlanetRepository());
    }

    @Bean
    public FarmRestConverter createFarmRestConverter() {
        return new FarmRestConverter();
    }


}

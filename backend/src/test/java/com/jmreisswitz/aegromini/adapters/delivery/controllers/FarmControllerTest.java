package com.jmreisswitz.aegromini.adapters.delivery.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmreisswitz.aegromini.AegroMiniApplication;
import com.jmreisswitz.aegromini.adapters.delivery.converters.FarmRestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FarmRest;
import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import com.jmreisswitz.aegromini.usecases.farm.AddFarmUseCase;
import com.jmreisswitz.aegromini.usecases.farm.DeleteFarmByIdUseCase;
import com.jmreisswitz.aegromini.usecases.farm.GetFarmByIdUseCase;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {AegroMiniApplication.class})
class FarmControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @MockBean
    private AddFarmUseCase addFarmUseCase;

    @MockBean
    private GetFarmByIdUseCase getFarmByIdUseCase;

    @MockBean
    private DeleteFarmByIdUseCase deleteFarmByIdUseCase;

    private RestConverter<FarmRest, Farm> restConverter;

    private FarmController farmController;

    private String fakeFarmId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();

        restConverter = new FarmRestConverter();
        fakeFarmId = RandomStringUtils.randomAlphanumeric(10);
        farmController = new FarmController(
                addFarmUseCase,
                getFarmByIdUseCase,
                deleteFarmByIdUseCase,
                restConverter
        );
    }

    @AfterEach
    void tearDown() {
        farmController = null;
        fakeFarmId = null;
    }

    @Test
    @DisplayName("Given a valid json with a farm" +
            "then should call addFarmUseCase.execute" +
            "and return status ok")
    void save_allGood_shouldReturnHttpCreated() throws Exception {
        String farmName = RandomStringUtils.randomAlphanumeric(10);
        FarmRest farmRest = new FarmRest(null, farmName, null);
        Farm savedFarm = new Farm(fakeFarmId, farmName, null);
        when(addFarmUseCase.execute(restConverter.mapToDomain(farmRest))).thenReturn(savedFarm);

        mockMvc.perform(MockMvcRequestBuilders.post("/aegro_mini/farm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(farmRest)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given a json with empty name" +
            "should return bad request")
    void save_emptyName_shouldReturnBadRequest() throws Exception{
        FarmRest farmRest = new FarmRest(null, "", null);
        mockMvc.perform(MockMvcRequestBuilders.post("/aegro_mini/farm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(farmRest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given an existing farm id" +
            "should return http 200")
    void getById_allGood_shouldReturnHttp200() throws Exception {
        // Arrange
        Farm farm = new Farm(fakeFarmId, RandomStringUtils.randomAlphanumeric(10), null);
        when(getFarmByIdUseCase.execute(fakeFarmId)).thenReturn(Optional.of(farm));

        // Act and Assert
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/aegro_mini/farm/" + fakeFarmId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
                );
    }

    @Test
    @DisplayName("Given a non existing farm id" +
            "should return http 404")
    void getById_nonExistingId_shouldReturnHttp404() throws Exception {
        when(getFarmByIdUseCase.execute(fakeFarmId)).thenThrow(FarmNotFoundException.class);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/aegro_mini/farm/"+fakeFarmId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Given a farm id" +
            "should call deleteFarmByIdUseCase.execute with farm id" +
            "and should return http 200")
    void delete_allGood_shouldPass() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/aegro_mini/farm/"+fakeFarmId)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(deleteFarmByIdUseCase, times(1)).execute(fakeFarmId);
    }
}

package com.jmreisswitz.aegromini.adapters.delivery.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmreisswitz.aegromini.AegroMiniApplication;
import com.jmreisswitz.aegromini.adapters.delivery.converters.FieldRestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.converters.RestConverter;
import com.jmreisswitz.aegromini.adapters.delivery.rest.FieldRest;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.AddFieldUseCase;
import com.jmreisswitz.aegromini.usecases.field.DeleteFieldByIdUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
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

import java.util.LinkedList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {AegroMiniApplication.class})
class FieldControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @MockBean
    private AddFieldUseCase addFieldUseCase;
    @MockBean
    private GetFieldByIdUseCase getFieldByIdUseCase;
    @MockBean
    private GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;
    @MockBean
    private DeleteFieldByIdUseCase deleteFieldByIdUseCase;

    private RestConverter<FieldRest, Field> restConverter;

    private FieldController fieldController;

    private String fakeFieldId;
    private String fakeFarmId;
    private String fieldName;
    private double fieldArea;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();

        restConverter = new FieldRestConverter();
        fakeFarmId = RandomStringUtils.randomAlphanumeric(10);
        fakeFieldId = RandomStringUtils.randomAlphanumeric(10);
        fieldName = RandomStringUtils.randomAlphanumeric(10);
        fieldArea = 10.0;
        fieldController = new FieldController(
                addFieldUseCase,
                getFieldByIdUseCase,
                getFieldsByFarmIdUseCase,
                deleteFieldByIdUseCase,
                restConverter
        );
    }

    @AfterEach
    void tearDown() {
        fieldController = null;
        fakeFarmId = null;
        fakeFieldId = null;
        fieldName = null;
    }

    @Test
    @DisplayName("Given a valid field rest" +
            "should return http 200")
    void save_allGood_shouldReturnHttp200() throws Exception {
        // Arrange
        FieldRest fieldRest = new FieldRest(null, fakeFarmId, fieldName, fieldArea);
        Field savedField = new Field(fakeFieldId, fakeFarmId, fieldName, fieldArea);
        when(addFieldUseCase.execute(restConverter.mapToDomain(fieldRest))).thenReturn(savedField);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/aegro_mini/field")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fieldRest)))
                .andExpect(status().isOk());  // Assert
    }

    @Test
    @DisplayName("Given a json with empty name" +
            "should return bad request")
    void save_emptyName_shouldReturnBadRequest() throws Exception{
        FieldRest fieldRest = new FieldRest(null, fakeFarmId, "", fieldArea);
        mockMvc.perform(MockMvcRequestBuilders.post("/aegro_mini/field")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fieldRest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getById_allGood_shouldReturnHttp200() throws Exception {
        // Arrange
        Field field = new Field(fakeFieldId, fakeFarmId, fieldName, fieldArea);
        when(getFieldByIdUseCase.execute(fakeFieldId)).thenReturn(Optional.of(field));

        // Act and Assert
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/aegro_mini/field/" + fakeFieldId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById_nonExistingId_shouldReturnHttp404() throws Exception {
        when(getFieldByIdUseCase.execute(fakeFieldId)).thenThrow(FieldNotFoundException.class);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/aegro_mini/field/"+fakeFieldId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getByFarmId_allGood_shouldReturnHttp200() throws Exception {
        // Arrange
        LinkedList<Field> fields = new LinkedList<>();
        fields.add(new Field(fakeFieldId, fakeFarmId, fieldName, fieldArea));
        when(getFieldsByFarmIdUseCase.execute(fakeFarmId)).thenReturn(fields);

        // Act
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/aegro_mini/field/byfarm/" + fakeFarmId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());  // Assert
    }

    @Test
    void getByFarmId_farmWithoutFields_shouldReturnHttp200() throws Exception {
        LinkedList<Field> fields = new LinkedList<>();
        when(getFieldsByFarmIdUseCase.execute(fakeFarmId)).thenReturn(fields);

        // Act
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/aegro_mini/field/byfarm/" + fakeFarmId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());  // Assert
    }

    @Test
    void delete_allGood_shouldPass() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/aegro_mini/field/"+fakeFieldId)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(deleteFieldByIdUseCase, times(1)).execute(fakeFieldId);
    }

}

package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

class DeleteAllFieldsByFarmIdUseCaseTest {
    @Mock
    private GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;
    @Mock
    private DeleteFieldByIdUseCase deleteFieldByIdUseCase;

    String farmId;
    String fieldId;

    private DeleteAllFieldsByFarmIdUseCase deleteAllFieldsByFarmIdUseCase;

    private List<Field> getFieldList() {
        fieldId = RandomStringUtils.randomAlphanumeric(10);
        List<Field> fieldList = new LinkedList<>();
        fieldList.add(new Field(fieldId, farmId, "name", 1.0));
        return fieldList;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        deleteAllFieldsByFarmIdUseCase = spy(
                new DeleteAllFieldsByFarmIdUseCase(getFieldsByFarmIdUseCase, deleteFieldByIdUseCase));
        farmId = RandomStringUtils.randomAlphanumeric(10);
    }

    @AfterEach
    void tearDown() {
        deleteAllFieldsByFarmIdUseCase = null;
    }

    @Test
    void execute_allGood_shouldPass() throws ProductionNotFoundException, FieldNotFoundException {
        List<Field> fieldList = getFieldList();
        when(getFieldsByFarmIdUseCase.execute(farmId)).thenReturn(fieldList);
        doNothing().when(deleteFieldByIdUseCase).execute(fieldId);

        deleteAllFieldsByFarmIdUseCase.execute(farmId);

        verify(getFieldsByFarmIdUseCase, times(1)).execute(farmId);
        verify(deleteFieldByIdUseCase, times(1)).execute(fieldId);
    }

}

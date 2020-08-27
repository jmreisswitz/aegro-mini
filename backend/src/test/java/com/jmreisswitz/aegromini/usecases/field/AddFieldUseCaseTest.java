package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import com.jmreisswitz.aegromini.usecases.farm.GetFarmByIdUseCase;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddFieldUseCaseTest {
    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private GetFarmByIdUseCase getFarmByIdUseCase;

    private AddFieldUseCase addFieldUseCase;

    private String fakeFarmId;
    private String fakeFieldId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        addFieldUseCase = spy(new AddFieldUseCase(fieldRepository, getFarmByIdUseCase));
        fakeFarmId = RandomStringUtils.randomAlphanumeric(10);
        fakeFieldId = RandomStringUtils.randomAlphanumeric(10);
    }

    @AfterEach
    void tearDown() {
        addFieldUseCase = null;
        fakeFarmId = null;
        fakeFieldId = null;
    }

    @Test
    void execute_allGood_shouldPass() {
        // Arrange
        String fieldName = RandomStringUtils.randomAlphanumeric(10);
        Field field = new Field(null, fakeFarmId, fieldName, 1);
        Answer<Field> repositoryAnswer = invocation -> new Field(fakeFieldId, fakeFarmId, fieldName, 1);
        Farm farm = new Farm(fakeFarmId, RandomStringUtils.randomAlphanumeric(10));
        when(fieldRepository.save(field)).thenAnswer(repositoryAnswer);
        when(getFarmByIdUseCase.execute(fakeFarmId)).thenReturn(Optional.of(farm));

        // Act
        Field savedField = null;
        try {
            savedField = addFieldUseCase.execute(field);
        } catch (IllegalArgumentException ex){
            fail("IllegalArgumentException thrown");
        }

        // Assert
        assertEquals(savedField.getId(), fakeFieldId);
        assertEquals(savedField.getFarmId(), fakeFarmId);
    }

    @Test
    void execute_nullField_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            addFieldUseCase.execute(null);
        });
    }

    @Test
    void execute_nonExistentFarmId_shouldFarmNotFoundException() {
        String fieldName = RandomStringUtils.randomAlphanumeric(10);
        Field field = new Field(null, fakeFarmId, fieldName, 1);
        when(getFarmByIdUseCase.execute(fakeFarmId)).thenThrow(FarmNotFoundException.class);
        assertThrows(FarmNotFoundException.class, () -> {
            addFieldUseCase.execute(field);
        });
    }

    @Test
    void execute_areaZero_shouldThrowIllegalArgumentException() {
        String fieldName = RandomStringUtils.randomAlphanumeric(10);
        Field field = new Field(null, fakeFarmId, fieldName, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            addFieldUseCase.execute(field);
        });
    }

    @Test
    void execute_areaNegative_shouldThrowIllegalArgumentException() {
        String fieldName = RandomStringUtils.randomAlphanumeric(10);
        Field field = new Field(null, fakeFarmId, fieldName, -1.0);
        assertThrows(IllegalArgumentException.class, () -> {
            addFieldUseCase.execute(field);
        });
    }

    @Test
    void execute_emptyName_shouldThrowIllegalArgumentException() {
        Field field = new Field(null, fakeFarmId, "", 1.0);
        assertThrows(IllegalArgumentException.class, () -> {
            addFieldUseCase.execute(field);
        });
    }

    @Test
    void execute_blankName_shouldThrowIllegalArgumentException() {
        Field field = new Field(null, fakeFarmId, "      ", 1.0);
        assertThrows(IllegalArgumentException.class, () -> {
            addFieldUseCase.execute(field);
        });
    }


}

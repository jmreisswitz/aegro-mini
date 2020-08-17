package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class GetFieldByIdUseCaseTest {
    @Mock
    private FieldRepository fieldRepository;

    private GetFieldByIdUseCase getFieldByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        getFieldByIdUseCase = spy(new GetFieldByIdUseCase(fieldRepository));
    }

    @AfterEach
    void tearDown() {
        getFieldByIdUseCase = null;
    }

    @Test
    @DisplayName("Given an existing fieldId" +
            "should not throw FieldNotFoundException" +
            "should return the field")
    void execute_allGood_shouldReturnTheField(){
        // Arrange
        String fakeId = RandomStringUtils.randomAlphanumeric(10);
        String fakeFarmId = RandomStringUtils.randomAlphanumeric(10);
        String fieldName = RandomStringUtils.randomAlphanumeric(10);
        Field field = new Field(fakeId, fakeFarmId, fieldName, 1);

        when(fieldRepository.findOneById(field.getId())).thenReturn(Optional.of(field));

        // Act
        Optional<Field> repoField = Optional.empty();
        try {
            repoField = getFieldByIdUseCase.execute(fakeId);
        } catch (FieldNotFoundException e){
            fail("FieldNotFoundException thrown");
        }

        // Assert
        Field repoFieldFinal = repoField.get();
        assertAll(
                () -> assertEquals(repoFieldFinal.getId(), fakeId),
                () -> assertEquals(repoFieldFinal.getName(), fieldName)
        );
    }

    @Test
    void execute_nonExistingFarmId_shouldThrowFarmNotFoundException(){
        // arrange
        String fakeId = RandomStringUtils.randomAlphanumeric(4);
        when(fieldRepository.findOneById(fakeId)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(FieldNotFoundException.class,
                () -> getFieldByIdUseCase.execute(fakeId)
        );
    }

}

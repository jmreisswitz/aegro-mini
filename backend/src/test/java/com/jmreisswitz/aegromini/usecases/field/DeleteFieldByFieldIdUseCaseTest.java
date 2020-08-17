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

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteFieldByFieldIdUseCaseTest {
    @Mock
    private FieldRepository fieldRepository;

    private DeleteFieldByIdUseCase deleteFieldByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        deleteFieldByIdUseCase = spy(new DeleteFieldByIdUseCase(fieldRepository));
    }

    @AfterEach
    void tearDown() {
        deleteFieldByIdUseCase = null;
    }

    @Test
    @DisplayName("Given an existing field id" +
            "then should not throw FieldNotFoundException" +
            "should call fieldRepository.delete")
    void execute_allGood_shouldPass() {
        // Arrange
        String fakeId = RandomStringUtils.randomAlphanumeric(10);
        Field field = new Field(fakeId, RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), 1);
        when(fieldRepository.findOneById(fakeId)).thenReturn(Optional.of(field));

        // Act
        try{
            deleteFieldByIdUseCase.execute(fakeId);
        } catch (FieldNotFoundException ex){
            fail("FieldNotFoundException thrown");
        }

        // Assert
        verify(fieldRepository, times(1)).delete(fakeId);
    }

    @Test
    @DisplayName("Given a non existing field id" +
            "should throw FieldNotFoundException")
    void execute_NonExistingFarm_shouldThrowFarmNotFoundException() {
        // Arrange
        String fakeId = RandomStringUtils.randomAlphanumeric(10);
        when(fieldRepository.findOneById(fakeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(FieldNotFoundException.class,
                () -> deleteFieldByIdUseCase.execute(fakeId)
        );
    }
}

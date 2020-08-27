package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.DeleteAllFieldsByFarmIdUseCase;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteFarmByIdUseCaseTest {
    @Mock
    private FarmRepository farmRepository;

    @Mock
    private DeleteAllFieldsByFarmIdUseCase deleteAllFieldsByFarmIdUseCase;

    private DeleteFarmByIdUseCase deleteFarmByIdUseCase;

    String fakeId;

    @BeforeEach
    void setUp() {
        fakeId = RandomStringUtils.randomAlphanumeric(10);
        MockitoAnnotations.initMocks(this);
        deleteFarmByIdUseCase = spy(new DeleteFarmByIdUseCase(farmRepository));
    }

    @AfterEach
    void tearDown() {
        deleteFarmByIdUseCase = null;
    }

    @Test
    @DisplayName("Given an existing farm id" +
            "then should not throw FarmNotFoundException" +
            "should call farmRepository.delete")
    void execute_allGood_shouldPass() throws FieldNotFoundException {
        // Arrange

        Farm farm = new Farm(fakeId, RandomStringUtils.randomAlphanumeric(10));
        when(farmRepository.findOneById(fakeId)).thenReturn(Optional.of(farm));
        doNothing().when(deleteAllFieldsByFarmIdUseCase).execute(fakeId);

        // Act
        deleteFarmByIdUseCase.execute(fakeId);

        // Assert
        verify(farmRepository, times(1)).delete(fakeId);
    }

    @Test
    @DisplayName("Given a non existing farm id" +
            "should throw FarmNotFoundException")
    void execute_NonExistingFarm_shouldThrowFarmNotFoundException() {
        // Arrange
        String fakeId = RandomStringUtils.randomAlphanumeric(10);
        when(farmRepository.findOneById(fakeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(FarmNotFoundException.class,
                () -> deleteFarmByIdUseCase.execute(fakeId)
        );
    }
}

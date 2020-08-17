package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.GetFieldsByFarmIdUseCase;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class GetFarmByIdUseCaseTest {
    @Mock
    private FarmRepository farmRepository;

    @Mock
    private GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;

    private GetFarmByIdUseCase getFarmByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        getFarmByIdUseCase = spy(new GetFarmByIdUseCase(farmRepository, getFieldsByFarmIdUseCase));
    }

    @AfterEach
    void tearDown() {
        getFieldsByFarmIdUseCase = null;
    }

    @Test
    @DisplayName("Given an existing farmId" +
            "should not throw FarmNotFoundException" +
            "should return the farm")
    void execute_allGood_shouldReturnTheFarm(){
        // Arrange
        String fakeId = RandomStringUtils.randomAlphanumeric(10);
        String farmName = RandomStringUtils.randomAlphanumeric(10);
        LinkedList<Field> fields = new LinkedList<>();
        Farm farm = new Farm(fakeId, farmName, null);

        when(getFieldsByFarmIdUseCase.execute(farm.getId())).thenReturn(fields);
        when(farmRepository.findOneById(farm.getId())).thenReturn(Optional.of(farm));

        // Act
        Optional<Farm> repoFarm = Optional.empty();
        try {
            repoFarm = getFarmByIdUseCase.execute(fakeId);
        } catch (FarmNotFoundException e){
            fail("FarmNotFoundException thrown");
        }

        // Assert
        Farm repoFarmFinal = repoFarm.get();
        assertAll(
                () -> assertEquals(repoFarmFinal.getId(), fakeId),
                () -> assertEquals(repoFarmFinal.getName(), farmName)
        );
    }

    @Test
    void execute_nonExistingFarmId_shouldThrowFarmNotFoundException(){
        // arrange
        String fakeId = RandomStringUtils.randomAlphanumeric(4);
        when(farmRepository.findOneById(fakeId)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(FarmNotFoundException.class,
                () -> getFarmByIdUseCase.execute(fakeId)
        );
    }

}

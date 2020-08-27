package com.jmreisswitz.aegromini.usecases.farm;

import com.jmreisswitz.aegromini.domain.Farm;
import com.jmreisswitz.aegromini.ports.repository.FarmRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class AddFarmUseCaseTest {
    @Mock
    private FarmRepository farmRepository;

    private AddFarmUseCase addFarmUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        addFarmUseCase = spy(new AddFarmUseCase(farmRepository));
    }

    @AfterEach
    void tearDown() {
        addFarmUseCase = null;
    }

    @Test
    @DisplayName("Given a valid farm" +
            "When call save" +
            "Should not throw IllegalArgumentException" +
            "Should return a farm with id")
    void save_allGood_shouldPass() {
        // Arrange
        String farmName = RandomStringUtils.randomAlphanumeric(10);
        Farm farm = new Farm(null, farmName);
        String fakeGeneratedId = RandomStringUtils.randomAlphabetic(10);
        Answer<Farm> repositoryAnswer = invocation -> new Farm(fakeGeneratedId, farmName);
        when(farmRepository.save(farm)).thenAnswer(repositoryAnswer);

        // Act
        Farm savedFarm = null;
        try{
            savedFarm = addFarmUseCase.execute(farm);
        } catch (IllegalArgumentException illegalArgumentException){
            fail("IllegalArgumentException");
        }
        Farm finalSavedFarm = savedFarm;

        // Assert
        assertAll(
                () -> assertEquals(finalSavedFarm.getId(), fakeGeneratedId),
                () -> assertEquals(finalSavedFarm.getName(), farmName)
        );
    }

    @Test
    @DisplayName("Given a null farm" +
            "Should throw IllegalArgumentException")
    void save_nullFarm_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {addFarmUseCase.execute(null);}
        );
    }

    @Test
    @DisplayName("Given a farm with a empty name" +
            "should throw IllegalArgumentException")
    void save_emptyName_shouldThrowIllegalArgumentException() {
        Farm farm = new Farm(null, "");
        assertThrows(IllegalArgumentException.class,
                () -> {addFarmUseCase.execute(farm);
        });
    }

}

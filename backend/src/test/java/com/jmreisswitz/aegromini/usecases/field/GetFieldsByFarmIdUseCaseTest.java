package com.jmreisswitz.aegromini.usecases.field;

import com.jmreisswitz.aegromini.ports.repository.FieldRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class GetFieldsByFarmIdUseCaseTest {
    @Mock
    private FieldRepository fieldRepository;

    private GetFieldsByFarmIdUseCase getFieldsByFarmIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        getFieldsByFarmIdUseCase = spy(new GetFieldsByFarmIdUseCase(fieldRepository));
    }

    @AfterEach
    void tearDown() {
        getFieldsByFarmIdUseCase = null;
    }

    @Test
    @DisplayName("Given a farm id" +
            "Should call fieldRepository.listAllByFarmId")
    void execute_allGood_shouldPass() {
        // Arrange
        String fakeFarmId = RandomStringUtils.randomAlphanumeric(10);

        // Act
        getFieldsByFarmIdUseCase.execute(fakeFarmId);

        // Assert
        verify(fieldRepository, times(1)).listAllByFarmId(fakeFarmId);
    }

}

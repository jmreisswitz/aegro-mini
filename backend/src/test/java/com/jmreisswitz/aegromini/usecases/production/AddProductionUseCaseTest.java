package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class AddProductionUseCaseTest {
    @Mock
    private ProductionRepository productionRepository;

    private AddProductionUseCase addProductionUseCase;

    private String productionId;
    private String fieldId;
    private String productionType;
    private double productionAmount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        addProductionUseCase = spy(new AddProductionUseCase(productionRepository));
        Random random = new Random();
        productionId = RandomStringUtils.randomAlphanumeric(10);
        fieldId = RandomStringUtils.randomAlphanumeric(10);
        productionType = RandomStringUtils.randomAlphanumeric(10);
        productionAmount = random.nextDouble();
    }

    @AfterEach
    void tearDown() {
        addProductionUseCase = null;
        fieldId = null;
        productionType = null;
    }

    @Test
    void save_allGood_shouldPass() {
        // Arrange
        Production production = new Production(null, fieldId, productionType, productionAmount);
        Production productionResponse = new Production(productionId, fieldId, productionType, productionAmount);
        when(productionRepository.save(production)).thenReturn(productionResponse);

        // Act
        Production productionAdded = addProductionUseCase.execute(production);

        // Assert
        assertAll(
                () -> assertEquals(productionAdded, productionResponse),
                () -> assertEquals(productionAdded.getFieldId(), production.getFieldId()),
                () -> assertEquals(productionAdded.getProductionType(), production.getProductionType())
        );
    }
}

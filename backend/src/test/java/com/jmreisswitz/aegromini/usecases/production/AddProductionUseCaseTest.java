package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Field;
import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.ports.repository.ProductionRepository;
import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import com.jmreisswitz.aegromini.usecases.field.GetFieldByIdUseCase;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class AddProductionUseCaseTest {
    @Mock
    private ProductionRepository productionRepository;

    @Mock
    private GetFieldByIdUseCase getFieldByIdUseCase;

    private AddProductionUseCase addProductionUseCase;

    private String productionId;
    private String fieldId;
    private String productionType;
    private double productionAmount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        addProductionUseCase = spy(new AddProductionUseCase(productionRepository, getFieldByIdUseCase));
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
    void save_allGood_shouldPass() throws FieldNotFoundException {
        // Arrange
        Production production = new Production(null, fieldId, productionType, productionAmount);
        Production productionResponse = new Production(productionId, fieldId, productionType, productionAmount);
        Field field = new Field(null, RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10), 1);
        when(productionRepository.save(production)).thenReturn(productionResponse);
        when(getFieldByIdUseCase.execute(production.getId())).thenReturn(Optional.of(field));

        // Act
        Production productionAdded = addProductionUseCase.execute(production);

        // Assert
        assertAll(
                () -> assertEquals(productionAdded, productionResponse),
                () -> assertEquals(productionAdded.getFieldId(), production.getFieldId()),
                () -> assertEquals(productionAdded.getProductionType(), production.getProductionType())
        );
    }

    @Test
    void save_nonExistentFieldId_shouldThrowFieldNotFoundException () throws FieldNotFoundException {
        Production production = new Production(null, fieldId, productionType, productionAmount);
        when(getFieldByIdUseCase.execute(fieldId)).thenThrow(FieldNotFoundException.class);

        assertThrows(FieldNotFoundException.class,
                () -> {addProductionUseCase.execute(production);}
        );
    }

    @Test
    void save_negativeProductionAmount_shouldThrowIllegalArgumentException () {
        Production production = new Production(null, fieldId, productionType, -1.0);
        assertThrows(IllegalArgumentException.class,
                () -> {
            addProductionUseCase.execute(production);
        });
    }

    @Test
    void save_zeroProductionAmount_shouldThrowIllegalArgumentException () {
        Production production = new Production(null, fieldId, productionType, 0.0);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    addProductionUseCase.execute(production);
                });
    }

    @Test
    void save_emptyProductionType_shouldThrowIllegalArgumentException () {
        Production production = new Production(null, fieldId, "", productionAmount);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    addProductionUseCase.execute(production);
                });
    }
}

package com.jmreisswitz.aegromini.usecases.productivity;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.domain.Productivity;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class CalculateProductivityUseCaseTest {

    private String fieldId;

    private CalculateProductivityUseCase calculateProductivityUseCase;

    private Production getProduction(String productionType, double amount) {
        return new Production(null, fieldId, productionType, amount);
    }

    private double getRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    private String getRandomString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    @BeforeEach
    void setUp() {
        fieldId = RandomStringUtils.randomAlphanumeric(10);
        calculateProductivityUseCase = spy(new CalculateProductivityUseCase());
    }

    @AfterEach
    void tearDown() {
        calculateProductivityUseCase = null;
    }

    @Test
    void execute_oneProduction_shouldPass() {
        Production production = getProduction(getRandomString(), getRandomDouble());
        List<Production> productionList = new LinkedList<>();
        productionList.add(production);
        double totalArea = production.getProductionAmount() + getRandomDouble();


        List<Productivity> productivityList = calculateProductivityUseCase
                .execute(productionList, totalArea);

        Productivity productivity = productivityList.get(0);
        assertAll(
                () -> assertEquals(productionList.size(), 1),
                () -> assertEquals(productivity.getProductivityAmount(), production.getProductionAmount()/totalArea),
                () -> assertEquals(productivity.getProductionType(), production.getProductionType())
        );
    }

    @Test
    void execute_twoDifferentProduction_shouldPass() {
        Production production1 = getProduction(getRandomString(), getRandomDouble());
        Production production2 = getProduction(getRandomString(), getRandomDouble());
        List<Production> productionList = new LinkedList<>();
        productionList.add(production1);
        productionList.add(production2);
        double totalArea;
        if (production1.getProductionAmount() > production2.getProductionAmount()) {
            totalArea = production1.getProductionAmount() + getRandomDouble();
        }
        else{
            totalArea = production2.getProductionAmount() + getRandomDouble();
        }

        List<Productivity> productivityList = calculateProductivityUseCase
                .execute(productionList, totalArea);


        Productivity productivity1 = productivityList.get(0);
        Productivity productivity2;
        if (production1.getProductionType().equals(productivity1.getProductionType())){
            productivity2 = productivityList.get(1);
        }
        else{
            productivity2 = productivityList.get(0);
            productivity1 = productivityList.get(1);
        }

        Productivity finalProductivity1 = productivity1;
        assertAll(
                () -> assertEquals(productionList.size(), 2),
                () -> assertEquals(production1.getProductionType(), finalProductivity1.getProductionType()),
                () -> assertEquals(production2.getProductionType(), productivity2.getProductionType()),
                () -> assertEquals(finalProductivity1.getProductivityAmount(), production1.getProductionAmount()/totalArea),
                () -> assertEquals(productivity2.getProductivityAmount(), production2.getProductionAmount()/totalArea)
        );
    }

    @Test
    void execute_twoSameProductionType_shouldPass() {
        String productionType = getRandomString();
        Production production1 = getProduction(productionType, getRandomDouble());
        Production production2 = getProduction(productionType, getRandomDouble());
        List<Production> productionList = new LinkedList<>();
        productionList.add(production1);
        productionList.add(production2);
        double totalArea;
        if (production1.getProductionAmount() > production2.getProductionAmount()) {
            totalArea = production1.getProductionAmount() + getRandomDouble();
        }
        else{
            totalArea = production2.getProductionAmount() + getRandomDouble();
        }

        List<Productivity> productivityList = calculateProductivityUseCase
                .execute(productionList, totalArea);


        Productivity productivity = productivityList.get(0);
        assertAll(
                () -> assertEquals(productivityList.size(), 1),
                () -> assertEquals(productivity.getProductionType(), productionType),
                () -> assertEquals(productivity.getProductivityAmount(),
                        (production1.getProductionAmount() + production2.getProductionAmount())/totalArea)
        );
    }
}

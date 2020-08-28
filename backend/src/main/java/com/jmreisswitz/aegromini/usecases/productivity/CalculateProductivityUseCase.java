package com.jmreisswitz.aegromini.usecases.productivity;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.domain.Productivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class CalculateProductivityUseCase {
    public List<Productivity> execute(List<Production> productionList, double totalArea) {
        Map<String, Double> productionsGrouped = groupByProductionType(productionList);
        return getProductivityList(productionsGrouped, totalArea);
    }

    private List<Productivity> getProductivityList(Map<String, Double> productionsGrouped, double totalArea) {
        List<Productivity> productivityList = new LinkedList<>();
        for (Map.Entry<String, Double> productionType : productionsGrouped.entrySet()) {
            productivityList.add(
                    new Productivity(productionType.getKey(), productionType.getValue()/ totalArea));
        }
        return productivityList;
    }

    private Map<String, Double> groupByProductionType(List<Production> productionList) {
        Map<String, Double> productionTypeMap = new HashMap<>();
        productionList.forEach(production -> insertProductionIntoMap(productionTypeMap, production));
        return productionTypeMap;
    }

    private void insertProductionIntoMap(Map<String, Double> productionTypeMap, Production production) {
        productionTypeMap.put(production.getProductionType(),
                production.getProductionAmount() +
                        productionTypeMap.getOrDefault(production.getProductionType(), 0.0));
    }
}

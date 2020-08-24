package com.jmreisswitz.aegromini.usecases.production;

import com.jmreisswitz.aegromini.domain.Production;
import com.jmreisswitz.aegromini.domain.Productivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class CalculateProductivityUseCase {
    public List<Productivity> execute(List<Production> productionList, double totalArea) {
        Map<String, Double> productionMap = getProductionsMap(productionList);
        return getProductivityList(productionMap, totalArea);
    }

    private List<Productivity> getProductivityList(Map<String, Double> productionMap, double totalArea) {
        List<Productivity> productivityList = new LinkedList<>();
        for (Map.Entry<String, Double> productionType : productionMap.entrySet()) {
            productivityList.add(
                    new Productivity(productionType.getKey(), productionType.getValue()/ totalArea);
        }
        return productivityList;
    }

    private Map<String, Double> getProductionsMap(List<Production> productionList) {
        Map<String, Double> productionTypeMap = new HashMap<>();
        for (Production production : productionList) {
            productionTypeMap.put(production.getProductionType(),
                    production.getProductionAmount() +
                            productionTypeMap.getOrDefault(production.getProductionType(), 0.0));
        }
        return productionTypeMap;
    }

}

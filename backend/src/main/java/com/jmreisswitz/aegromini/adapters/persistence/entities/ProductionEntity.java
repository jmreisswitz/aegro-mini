package com.jmreisswitz.aegromini.adapters.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Document("production")
public class ProductionEntity extends AbstractAuditingEntity implements Serializable {
    String id;
    @NonNull String fieldId;
    String productionType;
    Double productionAmount;
}

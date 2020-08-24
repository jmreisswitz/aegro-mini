package com.jmreisswitz.aegromini.adapters.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@AllArgsConstructor
@Data
@Document("fields")
public class FieldEntity extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue
    private String id;

    @NonNull
    private String farmId;

    private String name;

    @NonNull
    private double area;
}

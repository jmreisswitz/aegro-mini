package com.jmreisswitz.aegromini.adapters.persistence.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="farms")
public class FarmEntity extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue()
    private String id;

    @NonNull
    private String name;
}

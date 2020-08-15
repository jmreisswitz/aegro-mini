package com.jmreisswitz.aegromini.adapters.persistence.entities;

import com.jmreisswitz.aegromini.domain.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="FARM")
public class FarmEntity implements Serializable {
    @Id
    @GeneratedValue()
    private String id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "field")
    private HashMap<String, Field> fields;
}

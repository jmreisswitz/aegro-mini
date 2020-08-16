package com.jmreisswitz.aegromini.adapters.persistence.entities;

import com.jmreisswitz.aegromini.domain.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="farms")
public class FarmEntity implements Serializable {
    @Id
    @GeneratedValue()
    private String id;

    @NonNull
    private String name;

//    @OneToMany(mappedBy = "fields")
//    private Field fields;
}

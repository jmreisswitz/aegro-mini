package com.jmreisswitz.aegromini.adapters.persistence.entities;

import com.jmreisswitz.aegromini.domain.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmEntity implements Serializable {
    @Id
    @GeneratedValue()
    private String id;

    @OneToMany(mappedBy = "field")
    private HashMap<String, Field> fields;
}

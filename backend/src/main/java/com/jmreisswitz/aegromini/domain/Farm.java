package com.jmreisswitz.aegromini.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Farm implements Serializable {
    private String id;
    @NonNull private String name;
    private List<Field> fields;

//    public void setFields(List<Field> fields){
//        this.fields = fields;
//    }
}

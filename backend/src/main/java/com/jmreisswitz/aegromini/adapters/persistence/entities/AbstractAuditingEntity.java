package com.jmreisswitz.aegromini.adapters.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class AbstractAuditingEntity {
    @CreatedDate
    @Field("created_date")
//    @JsonIgnore
    private DateTime createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DateTime lastModifiedDate = DateTime.now();
}

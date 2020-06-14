package com.driversandassistantsorganizationapp.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Role {
    @Id
    private String id;
    private String organization;
    private String firstName;
    private String lastName;
    @Indexed(unique=true)
    private String nicNo;
    private String roleType;
    private Date createdDate;
    private Date lastModifiedDate;


}

package com.driversandassistantsorganizationapp.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Role {
    private String organization;
    private String firstName;
    private String lastName;
    @Id
    private String nicNo;
    private String roleType;
    private Date createdDate;
    private Date lastModifiedDate;


    public Role() {}
}

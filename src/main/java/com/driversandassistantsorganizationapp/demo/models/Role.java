package com.driversandassistantsorganizationapp.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Role {
    private String organization;
    private String firstName;
    private String lastName;
    private String nicNo;
    private String roleType;

    public Role() {}
}

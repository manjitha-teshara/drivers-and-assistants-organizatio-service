package com.driversandassistantsorganizationapp.demo.model_dtos.role;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class RolePutDTO {
    private String organization;
    private String firstName;
    private String lastName;
    private String nicNo;
    private String roleType;
    private Date lastModifiedDate;
}

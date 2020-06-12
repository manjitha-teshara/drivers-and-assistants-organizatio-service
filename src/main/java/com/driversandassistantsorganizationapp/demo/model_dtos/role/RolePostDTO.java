package com.driversandassistantsorganizationapp.demo.model_dtos.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class RolePostDTO {
    private String organization;
    private String firstName;
    private String lastName;
    private String nicNo;
    private String roleType;
}

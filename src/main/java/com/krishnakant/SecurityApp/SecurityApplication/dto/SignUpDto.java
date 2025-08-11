package com.krishnakant.SecurityApp.SecurityApplication.dto;

import com.krishnakant.SecurityApp.SecurityApplication.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;

    //demo purpose
    private Set<Role> roles;
}

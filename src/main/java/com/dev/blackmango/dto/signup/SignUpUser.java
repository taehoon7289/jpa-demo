package com.dev.blackmango.dto.signup;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class SignUpUser {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}

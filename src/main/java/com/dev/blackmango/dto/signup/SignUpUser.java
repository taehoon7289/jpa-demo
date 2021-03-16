package com.dev.blackmango.dto.signup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter @Setter
public class SignUpUser {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;

    private String name;
}

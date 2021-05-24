package com.dev.blackmango.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter @Setter
public class SignUpDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;

    private String name;
}

package com.dev.blackmango.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SignInDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}

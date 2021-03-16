package com.dev.blackmango.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultVO {
    int code;
    String message;
    Object data;
}

package com.dev.blackmango.common.exception.code;

import lombok.Getter;

@Getter
public enum BoardExCode implements BaseExCode {

  NO_DATA(-2001, "정보 없음!!!!!!");

  Integer code;
  String msg;

  BoardExCode(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}

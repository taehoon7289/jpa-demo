package com.dev.blackmango.common.exception.code;

import lombok.Getter;

@Getter
public enum UserExCode implements BaseExCode {

  INVALID_ID(-1001, "아이디 없음!!!!!!"),
  INVALID_PASSWORD(-1002, "비밀번호 없음!!!!!!");

  Integer code;
  String msg;

  UserExCode(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}

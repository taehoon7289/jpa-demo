package com.dev.blackmango.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {

  int code;
  String message;
  T data;

  public ResponseData(int code, String message) {
    this.code = code;
    this.message = message;
  }
}

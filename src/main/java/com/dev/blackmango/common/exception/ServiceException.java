package com.dev.blackmango.common.exception;

import com.dev.blackmango.common.exception.code.BaseExCode;
import lombok.Getter;

@Getter
public class ServiceException extends Exception {

  Integer code;

  public ServiceException(BaseExCode baseExCode) {
    super(baseExCode.getMsg());
    this.code = baseExCode.getCode();
  }
}

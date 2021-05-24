package com.dev.blackmango.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JWTDataDTO {

  long userNo;
  String id;
  String name;
}

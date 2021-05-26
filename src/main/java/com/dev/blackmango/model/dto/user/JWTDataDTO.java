package com.dev.blackmango.model.dto.user;

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

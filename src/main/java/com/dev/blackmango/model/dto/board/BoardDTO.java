package com.dev.blackmango.model.dto.board;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoardDTO {

  @NotNull
  private Integer boardNo;
  @NotEmpty
  private String title;
  @NotEmpty
  private String contents;

}

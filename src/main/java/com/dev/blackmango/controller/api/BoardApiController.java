package com.dev.blackmango.controller.api;

import com.dev.blackmango.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/boards")
public class BoardApiController {

  private BoardService boardService;

}

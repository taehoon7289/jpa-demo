package com.dev.blackmango.controller.api;

import com.dev.blackmango.common.component.JwtTokenProvider;
import com.dev.blackmango.common.exception.ServiceException;
import com.dev.blackmango.common.model.ResponseData;
import com.dev.blackmango.model.dto.board.BoardDTO;
import com.dev.blackmango.service.BoardService;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/boards")
public class BoardApiController {

  private BoardService boardService;
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/")
  public ResponseData postLongSaveBoard(
      @RequestHeader(value = "Authorization") String token,
      @RequestBody @Valid BoardDTO boardDTO) throws ServiceException {
    Map<String, Object> tokenMap = jwtTokenProvider.getData(token);
    return new ResponseData(1, "",
        boardService
            .saveBoard(Long.parseLong(tokenMap.getOrDefault("userNo", -1).toString()), boardDTO));
  }

  @GetMapping("/")
  public ResponseData getBoard(
      @RequestHeader(value = "Authorization") String token,
      @RequestParam String title) throws ServiceException {
    Map tokenMap = jwtTokenProvider.getData(token);
    return new ResponseData(1, "", boardService.getBoard(title));
  }
}

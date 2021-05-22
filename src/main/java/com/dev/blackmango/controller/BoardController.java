package com.dev.blackmango.controller;

import com.dev.blackmango.common.vo.ResultVO;
import com.dev.blackmango.dto.signup.SignUpForm;
import com.dev.blackmango.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

  private UserService userService;

  @GetMapping("/")
  public ResultVO getTest(HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getListUsers());
  }

  @PostMapping("/users")
  public ResultVO postUser(@RequestBody @Valid SignUpForm signUpForm,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.postUsers(signUpForm));
  }

  @GetMapping("/users/id")
  public ResultVO postTestId(@RequestParam String id,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersById(id));
  }

  @GetMapping("/users/name")
  public ResultVO postTestName(@RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersByName(name));
  }

  @GetMapping("/users/name/list")
  public ResultVO postTestNameList(@RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersByNameLike(name));
  }

  @GetMapping("/users/params")
  public ResultVO postTestParams(@RequestParam String id, @RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersByIdAndName(id, name));
  }

  @GetMapping("/users/page")
  public ResultVO postTestPage(@RequestParam int page, @RequestParam int limit,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersPage(page, limit));
  }

  @GetMapping("/users/page/params")
  public ResultVO postTestPageParams(@RequestParam int page, @RequestParam int limit,
      @RequestParam String id, @RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersPageByIdAndName(page, limit, id, name));
  }
}

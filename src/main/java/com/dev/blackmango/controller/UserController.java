package com.dev.blackmango.controller;

import com.dev.blackmango.common.vo.ResultVO;
import com.dev.blackmango.dto.signup.SignUpForm;
import com.dev.blackmango.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

  private UserService userService;

  @GetMapping("/")
  public ResultVO getListUser(HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getListUsers());
  }

  @PostMapping("/")
  public ResultVO postUser(@RequestBody @Valid SignUpForm signUpForm,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.postUsers(signUpForm));
  }

  @GetMapping("/id")
  public ResultVO getUserById(@RequestParam String id,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersById(id));
  }

  @GetMapping("/name")
  public ResultVO getListUserByName(@RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersByName(name));
  }

  @GetMapping("/name/list")
  public ResultVO getListUserByNameLike(@RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersByNameLike(name));
  }

  @GetMapping("/params")
  public ResultVO getTestParams(@RequestParam String id, @RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResultVO(1, "", userService.getUsersByIdAndName(id, name));
  }

  @GetMapping("/page")
  public ResultVO getTestPage(@RequestParam int page, @RequestParam int limit,
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

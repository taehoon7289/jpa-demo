package com.dev.blackmango.controller.admin;

import com.dev.blackmango.common.model.ResponseData;
import com.dev.blackmango.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserAdminController {

  private UserService userService;

  @GetMapping("/")
  public ResponseData getList(HttpServletRequest request, HttpServletResponse response) {
    return new ResponseData(1, "", userService.getListUsers());
  }

  @GetMapping("/id/{id}")
  public ResponseData getUserById(@PathVariable String id,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResponseData(1, "", userService.getUsersById(id));
  }

  @GetMapping("/name/{name}")
  public ResponseData getListByNameLike(@PathVariable String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResponseData(1, "", userService.getUsersByNameLike(name));
  }

  @GetMapping("/query")
  public ResponseData getUserByQuery(@RequestParam String id, @RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResponseData(1, "", userService.getUsersByIdAndName(id, name));
  }

  @GetMapping("/page")
  public ResponseData getTestPage(@RequestParam int page, @RequestParam int limit,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResponseData(1, "", userService.getUsersPage(page, limit));
  }

  @GetMapping("/users/page/params")
  public ResponseData postTestPageParams(@RequestParam int page, @RequestParam int limit,
      @RequestParam String id, @RequestParam String name,
      HttpServletRequest request, HttpServletResponse response) {
    return new ResponseData(1, "", userService.getUsersPageByIdAndName(page, limit, id, name));
  }
}

package com.dev.blackmango.controller.api;

import com.dev.blackmango.common.exception.ServiceException;
import com.dev.blackmango.common.model.ResponseData;
import com.dev.blackmango.model.dto.user.SignInDTO;
import com.dev.blackmango.model.dto.user.SignUpDTO;
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
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/sign")
public class SignApiController {

  private UserService userService;

  @PostMapping("/in")
  public ResponseData postUserSignIn(@RequestBody @Valid SignInDTO signInDTO,
      HttpServletRequest request, HttpServletResponse response) throws ServiceException {
    return new ResponseData(1, "", userService.signIn(signInDTO, request, response));
  }

  @PostMapping("/up")
  public ResponseData postUserSignUp(@RequestBody @Valid SignUpDTO signUpDTO,
      HttpServletRequest request, HttpServletResponse response) throws ServiceException {
    return new ResponseData(1, "", userService.signUp(signUpDTO, request, response));
  }

  @GetMapping("/out")
  public ResponseData getLongSignOut(HttpServletRequest request, HttpServletResponse response) {
    return new ResponseData(1, "", null);
  }

}

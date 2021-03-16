package com.dev.blackmango.controller;

import com.dev.blackmango.common.vo.ResultVO;
import com.dev.blackmango.dto.signup.SignUpUser;
import com.dev.blackmango.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/test")
public class TestController {

    private TestService testService;

    @GetMapping("/")
    public ResultVO getTest(HttpServletRequest request, HttpServletResponse response) {
        log.info("path :: /");
        log.info("request :: {}", request);
        log.info("response :: {}", response);
        return new ResultVO(1, "", testService.getListUsers());
    }

    @PostMapping("/user")
    public ResultVO postTest(@RequestBody @Valid SignUpUser signUpUser,
                             HttpServletRequest request, HttpServletResponse response) {
        log.info("path :: /user");
        log.info("request :: {}", request);
        log.info("response :: {}", response);
        return new ResultVO(1, "", testService.postUsers(signUpUser));
    }
}

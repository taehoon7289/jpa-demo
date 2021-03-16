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
        return new ResultVO(1, "", testService.getListUsers());
    }

    @PostMapping("/users")
    public ResultVO postTest(@RequestBody @Valid SignUpUser signUpUser,
                             HttpServletRequest request, HttpServletResponse response) {
        return new ResultVO(1, "", testService.postUsers(signUpUser));
    }

    @GetMapping("/users/id")
    public ResultVO postTestId(@RequestParam String id,
                               HttpServletRequest request, HttpServletResponse response) {
        return new ResultVO(1, "", testService.getUsersById(id));
    }

    @GetMapping("/users/name")
    public ResultVO postTestName(@RequestParam String name,
                                 HttpServletRequest request, HttpServletResponse response) {
        return new ResultVO(1, "", testService.getUsersByName(name));
    }

    @GetMapping("/users/name/list")
    public ResultVO postTestNameList(@RequestParam String name,
                                 HttpServletRequest request, HttpServletResponse response) {
        return new ResultVO(1, "", testService.getUsersByNameLike(name));
    }

    @GetMapping("/users/params")
    public ResultVO postTestParams(@RequestParam String id, @RequestParam String name,
                                   HttpServletRequest request, HttpServletResponse response) {
        return new ResultVO(1, "", testService.getUsersByIdAndName(id, name));
    }

    @GetMapping("/users/page")
    public ResultVO postTestPage(@RequestParam int page, @RequestParam int limit,
                                 HttpServletRequest request, HttpServletResponse response) {
        return new ResultVO(1, "", testService.getUsersPage(page, limit));
    }

    @GetMapping("/users/page/params")
    public ResultVO postTestPageParams(@RequestParam int page, @RequestParam int limit,
                                 @RequestParam String id, @RequestParam String name,
                                 HttpServletRequest request, HttpServletResponse response) {
        return new ResultVO(1, "", testService.getUsersPageByIdAndName(page, limit, id, name));
    }
}

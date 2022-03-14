package com.devh.auth.controller;

import com.devh.auth.service.UserService;
import com.devh.auth.vo.UserVO;
import com.devh.common.api.constant.ApiStatus;
import com.devh.common.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<Boolean> postRegister(@RequestBody UserVO userVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, userService.save(userVO));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("list")
    public ApiResponse<UserVO> getList() {
        try {
            List<UserVO> userVOList = userService.findAll();

            userVOList.forEach(System.out::println);

            return ApiResponse.success(ApiStatus.Success.OK, userVOList);
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("{username}")
    public ApiResponse<UserVO> get(@PathVariable("username") String username) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, userService.findByUsername(username));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}

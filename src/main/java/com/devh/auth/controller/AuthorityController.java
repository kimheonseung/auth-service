package com.devh.auth.controller;

import com.devh.auth.service.AuthorityService;
import com.devh.auth.vo.AuthorityVO;
import com.devh.common.api.constant.ApiStatus;
import com.devh.common.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authority")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;

    @GetMapping("list")
    public ApiResponse<AuthorityVO> getList() {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, authorityService.findAll());
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ApiResponse<AuthorityVO> get(@PathVariable("id") Long id) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, authorityService.findById(id));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("register")
    public ApiResponse<Boolean> postRegister(@RequestBody AuthorityVO authorityVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, authorityService.save(authorityVO));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("update")
    public ApiResponse<Boolean> postUpdate(@RequestBody AuthorityVO authorityVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, authorityService.update(authorityVO));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("delete")
    public ApiResponse<Boolean> postDelete(@RequestBody AuthorityVO authorityVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, authorityService.deleteById(authorityVO.getId()));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

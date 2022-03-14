package com.devh.auth.controller;

import com.devh.auth.service.UserTokenService;
import com.devh.auth.util.JwtTokenUtils;
import com.devh.auth.vo.TokenInformationVO;
import com.devh.auth.vo.TokenVO;
import com.devh.auth.vo.UserVO;
import com.devh.common.api.constant.ApiStatus;
import com.devh.common.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/token")
@RequiredArgsConstructor
public class UserTokenController {
    private final UserTokenService userTokenService;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("generate")
    public ApiResponse<TokenVO> postGenerateToken(@RequestBody UserVO userVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, userTokenService.generateToken(userVO));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("refresh")
    public ApiResponse<TokenVO> postRefresh(@RequestBody TokenVO tokenVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, userTokenService.refreshToken(tokenVO));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("information")
    public ApiResponse<TokenInformationVO> getTokenInformation(@RequestBody TokenVO tokenVO) {
        try {
            return ApiResponse.success(ApiStatus.Success.OK, jwtTokenUtils.getTokenInformationVO(tokenVO.getAccessToken()));
        } catch (Exception e) {
            return ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}

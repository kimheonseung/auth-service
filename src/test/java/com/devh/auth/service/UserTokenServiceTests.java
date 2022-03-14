package com.devh.auth.service;

import com.devh.auth.vo.TokenVO;
import com.devh.auth.vo.UserVO;
import com.devh.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserTokenServiceTests {
    @Autowired
    private UserTokenService userTokenService;

    //    @Test
    public void test_generateToken() throws ServiceException {
        TokenVO token = userTokenService.generateToken(
                UserVO.builder()
                        .username("root")
                        .password("bK5m/i6cms4hSHSronOsqKhqOUyqxTnjU8txYVbneWcPWVu1hOJV65BIAz6GPIIhNXrS+Q==")
                        .build()
        );
        System.out.println(token);
        assertTrue(StringUtils.isNotEmpty(token.getAccessToken()));
        assertTrue(StringUtils.isNotEmpty(token.getRefreshToken()));
    }

    @Test
    public void test_refreshToken() throws ServiceException {
        TokenVO t = TokenVO.builder()
                .accessToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXZoIiwic3ViIjoicm9vdCIsImlhdCI6MTY0NTYyNTQ2NiwiZXhwIjoxNjQ1NjI1NDk4fQ.P2OmwmcqvpKdyOTsng1SJZ09HMd1awWKw_PU9fh6eAg")
                .refreshToken("eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDU2MjU1NTcsImV4cCI6MTY0NjIzMDM1N30.pxrbA2tvNHTjZbpH-zMNcRD9XqIe7mJ2YLEqZHzGVDs")
                .build();

        System.out.println(t.getAccessToken());
        System.out.println(t.getRefreshToken());

//        jwtTokenUtils.isTokenExpired(t.getRefreshToken());

        TokenVO refreshT = userTokenService.refreshToken(t);
        System.out.println(refreshT.getTokenStatus());
        System.out.println(refreshT.getAccessToken());
        System.out.println(refreshT.getRefreshToken());

    }
}

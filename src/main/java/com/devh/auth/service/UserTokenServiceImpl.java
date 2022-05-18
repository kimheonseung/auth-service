package com.devh.auth.service;

import com.devh.auth.constant.TokenStatus;
import com.devh.auth.entity.UserToken;
import com.devh.auth.repository.UserTokenRepository;
import com.devh.auth.util.JwtTokenUtils;
import com.devh.auth.vo.TokenVO;
import com.devh.auth.vo.UserVO;
import com.devh.common.exception.ServiceException;
import com.devh.common.util.ExceptionUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserTokenServiceImpl implements UserTokenService {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserTokenRepository userTokenRepository;
    private final UserService userService;

    @Transactional
    @Override
    public TokenVO generateToken(UserVO userVO) throws ServiceException {
        try {
            UserVO authenticatedVO = userService.authenticate(userVO);
            TokenVO tokenVO = jwtTokenUtils.generateToken(authenticatedVO);

            Optional<UserToken> optionalUserToken = userTokenRepository.findByUserId(authenticatedVO.getId());

            UserToken userToken = optionalUserToken.orElse(UserToken.builder()
                    .user(userService.voToEntity(authenticatedVO))
                    .build());
            userToken.setRefreshToken(tokenVO.getRefreshToken());
            userTokenRepository.save(userToken);
            tokenVO.setTokenStatus(TokenStatus.LOGIN_SUCCESS);
            return tokenVO;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public TokenVO refreshToken(TokenVO tokenVO) throws ServiceException {
        try {
            final String accessToken = tokenVO.getAccessToken();
            final String refreshToken = tokenVO.getRefreshToken();

            String username = null;

            boolean isAccessTokenExpired = false;
            try {
                isAccessTokenExpired = jwtTokenUtils.isTokenExpired(accessToken);
                if(isAccessTokenExpired) {
                    username = jwtTokenUtils.getUsernameFromToken(accessToken);
                } else {
                    tokenVO.setTokenStatus(TokenStatus.ACCESS_TOKEN_NOT_EXPIRED);
                }
            } catch (ExpiredJwtException e) {
                username = e.getClaims().getSubject();
                isAccessTokenExpired = true;
            }

            if(isAccessTokenExpired) {
                try {
                    if(!jwtTokenUtils.isTokenExpired(refreshToken)) {
                        UserVO userVO = userService.findByUsername(username);
                        UserToken userToken = userTokenRepository.findByUserId(userVO.getId()).orElseThrow();
                        final String recordRefreshToken = userToken.getRefreshToken();
                        if(refreshToken.equals(recordRefreshToken)) {
                            TokenVO refreshTokenVO = jwtTokenUtils.generateToken(userVO);
                            tokenVO.setTokenStatus(TokenStatus.REFRESH_SUCCESS);
                            tokenVO.setAccessToken(refreshTokenVO.getAccessToken());
                            tokenVO.setRefreshToken(refreshTokenVO.getRefreshToken());
                            userToken.setRefreshToken(refreshTokenVO.getRefreshToken());
                            userTokenRepository.save(userToken);
                        } else {
                            tokenVO.setTokenStatus(TokenStatus.REFRESH_FAIL);
                        }
                    }
                } catch (ExpiredJwtException e) {
                    tokenVO.setTokenStatus(TokenStatus.LOGIN_REQUIRED);
                }
            }

            return tokenVO;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }
}

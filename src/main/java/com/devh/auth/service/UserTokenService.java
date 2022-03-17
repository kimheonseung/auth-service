package com.devh.auth.service;

import com.devh.auth.vo.TokenVO;
import com.devh.auth.vo.UserVO;
import com.devh.module.exception.ServiceException;

public interface UserTokenService {
    TokenVO generateToken(UserVO userVO) throws ServiceException;
    TokenVO refreshToken(TokenVO tokenVO) throws ServiceException;
}

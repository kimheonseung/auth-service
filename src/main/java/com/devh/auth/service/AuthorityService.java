package com.devh.auth.service;

import com.devh.auth.entity.Authority;
import com.devh.auth.vo.AuthorityVO;
import com.devh.common.exception.ServiceException;

import java.util.List;

public interface AuthorityService {
    default AuthorityVO entityToVO(Authority e) {
        if(e == null)
            return null;
        else
            return AuthorityVO.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .description(e.getDescription())
                .build();
    }
    default Authority voToEntity(AuthorityVO v) {
        if(v == null)
            return null;
        else
            return Authority.builder()
                    .id(v.getId())
                    .name(v.getName())
                    .description(v.getDescription())
                .build();
    }

    AuthorityVO findById(Long id) throws ServiceException;
    AuthorityVO findByName(String name) throws ServiceException;
    List<AuthorityVO> findAll() throws ServiceException;
    boolean save(AuthorityVO authorityVO) throws ServiceException;
    boolean update(AuthorityVO authorityVO) throws ServiceException;
    boolean deleteById(Long id) throws ServiceException;
}

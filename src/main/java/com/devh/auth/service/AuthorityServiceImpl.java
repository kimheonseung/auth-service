package com.devh.auth.service;

import com.devh.auth.entity.Authority;
import com.devh.auth.repository.AuthorityRepository;
import com.devh.auth.vo.AuthorityVO;
import com.devh.common.exception.ServiceException;
import com.devh.common.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public AuthorityVO findById(Long id) throws ServiceException {
        try {
            Optional<Authority> optionalAuthority = authorityRepository.findById(id);
            return entityToVO(optionalAuthority.orElseThrow());
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public AuthorityVO findByName(String name) throws ServiceException {
        try {
            Optional<Authority> optionalAuthority = authorityRepository.findByName(name);
            return entityToVO(optionalAuthority.orElseThrow());
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<AuthorityVO> findAll() throws ServiceException {
        try {
            List<Authority> authorityList = authorityRepository.findAll();
            List<AuthorityVO> authorityVOList = new ArrayList<>();
            authorityList.forEach(e -> authorityVOList.add(entityToVO(e)));
            return authorityVOList;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean save(AuthorityVO authorityVO) throws ServiceException {
        try {
            Authority authority = voToEntity(authorityVO);
            authorityRepository.save(authority);
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean update(AuthorityVO authorityVO) throws ServiceException {
        try {
            Authority dbAuthority = authorityRepository.findById(authorityVO.getId()).orElseThrow();
            final String requestName = authorityVO.getName();
            if(!dbAuthority.getName().equals(requestName)) {
                dbAuthority.setName(requestName);
            }
            final String requestDescription = authorityVO.getDescription();
            if(!dbAuthority.getDescription().equals(requestDescription)) {
                dbAuthority.setDescription(requestDescription);
            }
            authorityRepository.save(dbAuthority);
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            authorityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }
}

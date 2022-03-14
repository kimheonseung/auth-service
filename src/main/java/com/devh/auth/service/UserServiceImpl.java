package com.devh.auth.service;

import com.devh.auth.entity.User;
import com.devh.auth.repository.UserRepository;
import com.devh.auth.vo.UserVO;
import com.devh.common.exception.ServiceException;
import com.devh.common.util.AES256Utils;
import com.devh.common.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AES256Utils aes256Utils;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    @Override
    public UserVO findByUsername(String username) throws ServiceException {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            return entityToVO(optionalUser.orElseThrow());
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public List<UserVO> findAll() throws ServiceException {
        try {
            List<UserVO> userVOList = new ArrayList<>();
            List<User> userList = userRepository.findAll();
            userList.forEach(e -> userVOList.add(entityToVO(e)));
            return userVOList;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean save(UserVO userVO) throws ServiceException {
        try {
            final String requestPassword = userVO.getPassword();
            final String rawPassword = aes256Utils.decrypt(requestPassword);
            final String bcryptPassword = passwordEncoder.encode(rawPassword);
            userVO.setPassword(bcryptPassword);
            User savedUser = userRepository.save(voToEntity(userVO));
            return userVO.getUsername().equals(savedUser.getUsername());
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean update(UserVO userVO) throws ServiceException {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(userVO.getUsername());
            User dbUSer = optionalUser.orElseThrow();
//            dbUSer.set
            return false;
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public UserVO authenticate(UserVO userVO) throws ServiceException {
        try {
            UserVO dbUser = findByUsername(userVO.getUsername());
            String requestPassword = aes256Utils.decrypt(userVO.getPassword());
            if(passwordEncoder.matches(requestPassword, dbUser.getPassword())) {
                return dbUser;
            } else {
                throw new Exception("Authentication Failed");
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.stackTraceToString(e));
            throw new ServiceException(e.getMessage());
        }
    }
}

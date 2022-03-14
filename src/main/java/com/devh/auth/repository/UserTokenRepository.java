package com.devh.auth.repository;

import com.devh.auth.entity.UserToken;
import com.devh.common.exception.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByUserId(Long userId) throws ServiceException;
}

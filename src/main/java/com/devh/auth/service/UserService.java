package com.devh.auth.service;

import com.devh.auth.entity.Authority;
import com.devh.auth.entity.Department;
import com.devh.auth.entity.User;
import com.devh.auth.entity.UserAuthority;
import com.devh.auth.entity.UserDepartment;
import com.devh.auth.vo.AuthorityVO;
import com.devh.auth.vo.DepartmentVO;
import com.devh.auth.vo.UserVO;
import com.devh.common.exception.ServiceException;

import java.util.List;

public interface UserService {

    default UserVO entityToVO(User entity) {
        if(entity == null)
            return null;
        else {
            UserVO userVO = UserVO.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .password(entity.getPassword())
                    .name(entity.getName())
                    .nickname(entity.getNickname())
                    .email(entity.getEmail())
                    .phone(entity.getPhone())
                    .accessIp(entity.getAccessIp())
                    .loginFailIp(entity.getLoginFailIp())
                    .loginFailCount(entity.getLoginFailCount())
                    .statusMessage(entity.getStatusMessage())
                    .backgroundImage(entity.getBackgroundImage())
                    .profileImage(entity.getProfileImage())
                    .loginAt(entity.getLoginAt())
                    .loginFailedAt(entity.getLoginFailedAt())
                    .passwordChangedAt(entity.getPasswordChangedAt())
                    .build();
            for (UserAuthority userAuthority : entity.getAuthorities()) {
                Authority authority = userAuthority.getAuthority();
                userVO.getAuthorities().add(
                        AuthorityVO.builder()
                                .id(authority.getId())
                                .name(authority.getName())
                                .description(authority.getDescription())
                                .build()
                );
            }
            for (UserDepartment userDepartment : entity.getDepartments()) {
                Department department = userDepartment.getDepartment();
                userVO.getDepartments().add(
                        DepartmentVO.builder()
                                .id(department.getId())
                                .name(department.getName())
                                .description(department.getDescription())
                                .parentId(department.getDepartment() == null ? null : department.getDepartment().getId())
                                .build()
                );
            }

            return userVO;
        }
    }

    default User voToEntity(UserVO v) {
        if(v == null)
            return null;
        else {
            User user = User.builder()
                    .id(v.getId())
                    .username(v.getUsername())
                    .password(v.getPassword())
                    .name(v.getName())
                    .nickname(v.getNickname())
                    .email(v.getEmail())
                    .phone(v.getPhone())
                    .accessIp(v.getAccessIp())
                    .loginFailIp(v.getLoginFailIp())
                    .loginFailCount(v.getLoginFailCount())
                    .statusMessage(v.getStatusMessage())
                    .backgroundImage(v.getBackgroundImage())
                    .profileImage(v.getProfileImage())
                    .loginAt(v.getLoginAt())
                    .loginFailedAt(v.getLoginFailedAt())
                    .passwordChangedAt(v.getPasswordChangedAt())
                    .build();

            for(AuthorityVO a : v.getAuthorities()) {
                user.getAuthorities().add(
                        UserAuthority.builder()
                                .user(User.builder().id(v.getId()).build())
                                .authority(Authority.builder().id(a.getId()).build())
                                .build()
                );
            }

            for(DepartmentVO d : v.getDepartments()) {
                user.getDepartments().add(
                        UserDepartment.builder()
                                .user(User.builder().id(v.getId()).build())
                                .department(Department.builder().id(d.getId()).build())
                                .build()
                );
            }

            return user;
        }
    }

    List<UserVO> findAll() throws ServiceException;
    boolean save(UserVO userVO) throws ServiceException;
    boolean update(UserVO userVO) throws ServiceException;
    UserVO findByUsername(String username) throws ServiceException;
    UserVO authenticate(UserVO userVO) throws ServiceException;
}

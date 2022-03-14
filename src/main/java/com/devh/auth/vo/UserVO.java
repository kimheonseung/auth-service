package com.devh.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO extends AbstractVO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private String accessIp;
    private String loginFailIp;
    private Integer loginFailCount;
    private String statusMessage;
    private String backgroundImage;
    private String profileImage;
    private LocalDateTime loginAt;
    private LocalDateTime loginFailedAt;
    private LocalDateTime passwordChangedAt;
    private final Set<AuthorityVO> authorities = new LinkedHashSet<>();
    private final Set<DepartmentVO> departments = new LinkedHashSet<>();
}
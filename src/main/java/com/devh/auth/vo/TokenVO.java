package com.devh.auth.vo;

import com.devh.auth.constant.TokenStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TokenVO {
    private TokenStatus tokenStatus;
    private String accessToken;
    private String refreshToken;
}

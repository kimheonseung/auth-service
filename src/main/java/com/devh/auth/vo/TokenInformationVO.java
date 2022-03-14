package com.devh.auth.vo;

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
public class TokenInformationVO {
    private boolean validate;
    private String issuer;
    private String subject;
    private String audience;
    private String expiration;
    private String notBefore;
    private String issuedAt;
    private String id;
    private String description;
}

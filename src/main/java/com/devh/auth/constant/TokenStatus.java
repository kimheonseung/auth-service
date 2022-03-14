package com.devh.auth.constant;

/**
 * <pre>
 * Description :
 *     토큰 응답 상태 관련 상수
 * ===============================================
 * Member fields :
 *
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2022. 2. 23.
 * </pre>
 */
public enum TokenStatus {
    REFRESH_SUCCESS,
    REFRESH_FAIL,
    LOGIN_REQUIRED,
    LOGIN_SUCCESS,
    ACCESS_TOKEN_NOT_EXPIRED;
}

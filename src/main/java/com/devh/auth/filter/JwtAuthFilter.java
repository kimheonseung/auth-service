package com.devh.auth.filter;

import com.devh.auth.util.JwtTokenUtils;
import com.devh.common.api.constant.ApiStatus;
import com.devh.common.api.response.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = jwtTokenUtils.getTokenFromRequestHeader(request);

        if(token == null) {
            filterChain.doFilter(request, response);
        } else {
            try {
                if(jwtTokenUtils.validateToken(token)) {
                    filterChain.doFilter(request, response);
                } else {
                    throw new Exception("Token is invalid.");
                }
            } catch (ExpiredJwtException | MalformedJwtException e) {
                response.setStatus(ApiStatus.CustomError.TOKEN_ERROR.getCode());
                response.setContentType("application/json");
                response.getWriter().write(ApiResponse.customError(ApiStatus.CustomError.TOKEN_ERROR, e.getMessage()).convertToJsonString());
            } catch (Exception e) {
                response.setStatus(ApiStatus.ServerError.INTERNAL_SERVER_ERROR.getCode());
                response.setContentType("application/json");
                response.getWriter().write(ApiResponse.serverError(ApiStatus.ServerError.INTERNAL_SERVER_ERROR, e.getMessage()).convertToJsonString());
            }
        }
    }
}

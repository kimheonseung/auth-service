package com.devh.auth.util;

import com.devh.auth.vo.TokenInformationVO;
import com.devh.auth.vo.TokenVO;
import com.devh.auth.vo.UserVO;
import com.devh.module.exception.util.ExceptionUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtils {
    @Value("${jwt.auth.issuer}")
    private String issuer;
    @Value("${jwt.auth.secretKey}")
    private String secretKey;
    @Value("${jwt.auth.accessExpire}")
    private int accessExpire;
    @Value("${jwt.auth.refreshExpire}")
    private int refreshExpire;
    @Value("${jwt.auth.header}")
    private String header;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* default signature algorithm */
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * Rules
     * User 테이블의 username 를 Token의 Subject에 저장
     */
    public String getUsernameFromToken(String token) throws ExpiredJwtException, MalformedJwtException {
        try {
            return getClaimsFromToken(token).getSubject();
        } catch (ExpiredJwtException | MalformedJwtException je) {
            throw je;
        }
    }

    public String getUsernameFromRequest(HttpServletRequest request) {
        final Claims claims = this.getClaimsFromToken(this.getTokenFromRequestHeader(request));
        return claims.getSubject();
    }

    public TokenVO generateToken(UserVO userVO) {
        final Date now = new Date();
        final String accessToken = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(userVO.getUsername())
                .setIssuedAt(now)
                .setExpiration(generateAccessExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        final String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(generateRefreshExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        return TokenVO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public boolean validateToken(String token) {
        final String tokenUsername = this.getUsernameFromToken(token);
        return (
                tokenUsername != null && !isTokenExpired(token)
        );
    }

    public boolean isRefreshNecessary(String token) {
        final String tokenUsername = this.getUsernameFromToken(token);
        return (
                tokenUsername != null && isTokenExpired(token)
        );
    }

    public String getTokenFromRequestHeader(HttpServletRequest request) {
        return request.getHeader(header);
    }

    public TokenInformationVO getTokenInformationVO(String token) {
        try {
            final Claims claims = getClaimsFromToken(token);

            return TokenInformationVO.builder()
                    .validate(true)
                    .issuer(claims.getIssuer())
                    .subject(claims.getSubject())
                    .audience(claims.getAudience())
                    .expiration(parseDate(claims.getExpiration()))
                    .notBefore(parseDate(claims.getNotBefore()))
                    .issuedAt(parseDate(claims.getIssuedAt()))
                    .id(claims.getId())
                    .build();
        } catch (ExpiredJwtException | MalformedJwtException e) {
            return TokenInformationVO.builder().validate(false).description(e.getMessage()).build();
        }
    }

    private Date generateAccessExpirationDate() {
        final long nowMillis = System.currentTimeMillis();
        return new Date(nowMillis + (accessExpire * 1000L));
    }

    private Date generateRefreshExpirationDate() {
        final long nowMillis = System.currentTimeMillis();
        return new Date(nowMillis + (refreshExpire * 1000L));
    }

    private Claims getClaimsFromToken(String token) throws ExpiredJwtException, MalformedJwtException {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | MalformedJwtException eje) {
            log.warn(ExceptionUtils.stackTraceToString(eje));
            throw eje;
        }
    }

    public boolean isTokenExpired(String token) throws ExpiredJwtException, MalformedJwtException {
        Date expireDate = getExpirationDate(token);
        return expireDate.before(new Date());
    }

    private Date getExpirationDate(String token) throws ExpiredJwtException, MalformedJwtException {
        final Claims claims = this.getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private String parseDate(Date date) {
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}

package com.siemens.iconnector.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

/**
 * @author: liangjie.feng
 * @date: 2022/8/10 14:14
 */
public class JwtUtils {

    /**
     *
     * privateKey sign token
     *
     * @param subject    data
     * @param privateKey privateKey
     * @param expire     expire time
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(String subject, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .setSubject(subject)
                .setId(createJTI())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * publicKey analysis token
     *
     * @param token     token
     * @param publicKey publicKey
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * get payload from token
     * @param token
     * @param publicKey
     * @return
     */
    public static String getMessageFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return body.getSubject();
    }
}

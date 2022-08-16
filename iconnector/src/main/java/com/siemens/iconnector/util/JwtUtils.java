package com.siemens.iconnector.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.io.ByteArrayInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import static com.siemens.iconnector.constant.ConstantValus.SIGNER_CERTIFICATE;

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
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(String subject, PrivateKey privateKey, Map<String, Object> headerMap) {
        return Jwts.builder()
                .setSubject(subject)
                .setId(createJTI())
                .setHeader(headerMap)
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

    public static String getCertificateFromHeader(String token) throws JoseException {
        JsonWebSignature jsonWebSignature = (JsonWebSignature) JsonWebSignature.fromCompactSerialization(token);
        return jsonWebSignature.getHeader(SIGNER_CERTIFICATE);
    }

    public static PublicKey getPublicKeyFromCertificate(String certificate0) throws Exception {
        byte[] decode = Base64.getDecoder().decode(certificate0);

        ByteArrayInputStream bis = new ByteArrayInputStream(decode);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate certificate = cf.generateCertificate(bis);

        PublicKey publicKey = certificate.getPublicKey();
        System.out.println(publicKey);
        return publicKey;
    }
}

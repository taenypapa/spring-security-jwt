package com.agile.demo.core.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {

    public static JwtPayload initJwtPayload(String bearerJwt) {

        bearerJwt = StringUtils.removeStart(bearerJwt, "Bearer ");

        try {
            DecodedJWT jwt = JWT.decode(bearerJwt);
            Map<String, Claim> claims = jwt.getClaims();

            JwtPayload jwtPayload = JwtPayload.builder()
                    .name(claims.get("name") != null ? claims.get("name").asString():null)
                    .userName(claims.get("user_name") != null ? claims.get("user_name").asString():null)
                    .scope(claims.get("scope") != null ? claims.get("scope").asList(String.class):null)
                    .exp(claims.get("exp") != null ? claims.get("exp").asLong():null)
                    .authorities(claims.get("authorities") != null ? claims.get("authorities").asList(String.class):null)
                    .jti(claims.get("jti") != null ? claims.get("jti").asString():null)
                    .clientId(claims.get("client_id") != null ? claims.get("client_id").asString():null)
                    .customValue(claims.get("customValue") != null ? claims.get("customValue").asString():null)
                    .build();

            log.info(jwtPayload.toString());

            return jwtPayload;

        } catch (Exception e) {
        	log.error("build Jwt Error: {}", e.getCause().getMessage());
            return null;
        }
    }

    public static String getJwtToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}


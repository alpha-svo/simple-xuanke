package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.Student;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTUtils {
    private static final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Student stu, HttpServletRequest request) {
        String access_token = JWT.create()
                .withSubject(stu.getStuname())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //10分钟有效
                .withIssuer(request.getRequestURL().toString())
                .withClaim("pwd", stu.getPassword())
                .sign(algorithm);
        return access_token;
    }

    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            System.out.println("签发给：" + decodedJWT.getSubject());
            return true;
        } catch (Exception e) {
            System.out.println("token验证错误");
            return false;
        }
    }

    public static String getSubject(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}

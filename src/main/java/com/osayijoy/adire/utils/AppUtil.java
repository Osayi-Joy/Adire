package com.osayijoy.adire.utils;

import com.osayijoy.adire.dto.response.AppResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Optional;

@Slf4j
public class AppUtil {

    @Bean
    public static Optional<String> extractEmailFromPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return Optional.of(authentication.getName());
        return Optional.empty();
    }


    public static String getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "system";
        }
        return authentication.getName();
    }


    public static String generateResetPassword(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*30)))
                .signWith(SignatureAlgorithm.HS384, "something")
                .compact();
    }

    public static <T> ResponseEntity<AppResponse> errorResponse(HttpStatus status, String errMsg){
        AppResponse <T> ar = new AppResponse<>(status);
        ar.setMessage(errMsg);
        ar.setStatus(true);
        ar.getTime();
        return new ResponseEntity<>(ar,status);
    }




}

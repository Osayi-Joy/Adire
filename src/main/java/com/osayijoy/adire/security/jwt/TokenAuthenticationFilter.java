package com.osayijoy.adire.security.jwt;

import com.osayijoy.adire.exception.CustomException;
import com.osayijoy.adire.exception.UnAuthorizedException;
import com.osayijoy.adire.utils.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {






    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, CustomException {
        String requestPathUrl = request.getRequestURL().toString();
        log.info("endpoint request was sent to is : {}",requestPathUrl);
        if (request.getServletPath().equals("/auth/signup") ) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            log.info(authorizationHeader);
            if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                try {
                    String token = authorizationHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
                    UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.validateTokenAndGetJws(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (UnAuthorizedException exception) {
                    exception.printStackTrace();
                    log.error("Error occurred {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                } catch (Exception exception) {
                    exception.printStackTrace();
                    log.error("Error occurred {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "An error occurred while processing your request");
                }

            } else {
                filterChain.doFilter(request, response);
            }
        }
    }


}

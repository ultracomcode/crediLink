package com.main.CrediLink.domain.config.security.exeptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, Object> inner = new HashMap<>();
        inner.put("status", 401);

        if (authException instanceof BadCredentialsException) {
            inner.put("error", "Senha incorreta");
        } else {
            inner.put("error", "Usuário não encontrado");
        }

        Map<String, Object> body = new HashMap<>();
        body.put("data", inner);

        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}



package com.main.CrediLink.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountCodeNotFoundException.class)
    public ResponseEntity<Object> handleAccountCodeNotFound(AccountCodeNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("timestamp", ZonedDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SoapIntegrationException.class)
    public ResponseEntity<ErrorResponse> handleSoapIntegration(SoapIntegrationException ex, HttpServletRequest request) {
        String safeDetail = StringEscapeUtils.escapeHtml4(ex.getMessage() != null ? ex.getMessage() : "Usuário principal inválido");
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_GATEWAY.value(),
                "Erro ao comunicar com o sistema externo",
                safeDetail,
                request.getRequestURI(),
                Instant.now().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }

}

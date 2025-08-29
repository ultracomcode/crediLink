package com.main.CrediLink.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", "Violação de chave única");
        errorDetails.put("message", extractConstraintMessage(ex));
        errorDetails.put("status", HttpStatus.CONFLICT.value());

        Map<String, Object> body = new HashMap<>();
        body.put("data", errorDetails);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    private String extractConstraintMessage(DataIntegrityViolationException ex) {
        String msg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        if (msg != null && msg.contains("usuarios_cpfcnpj_key")) {
            return "CPF/CNPJ informado já está cadastrado.";
        }
        if (msg != null && msg.contains("usuarios_email_key")) {
            return "Email informado já está cadastrado.";
        }
        if (msg != null && msg.contains("usuarios_username_key")) {
        return "Username já cadastrado.";
        }
        if (msg != null && msg.contains("usuarios_idcrm_key")) {
            return "IdCrm já cadastrado.";
        }
        return "Violação de restrição de dados.";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Lista todos os campos com erros
        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> field = new HashMap<>();
                    field.put("field", error.getField());
                    field.put("message", error.getDefaultMessage());
                    return field;
                })
                .collect(Collectors.toList());

        // Monta o JSON final
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", "Erro de validação");
        errorDetails.put("status", 400);
        errorDetails.put("fields", fieldErrors);

        Map<String, Object> body = new HashMap<>();
        body.put("data", errorDetails);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}

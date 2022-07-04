package ru.chel.SRMPlayGround.controller.utils;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BindingValidate {
    private static final Logger logger = LoggerFactory.getLogger(BindingValidate.class);

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                FieldError::getField,
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public static ResponseEntity<?> getResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = getErrors(bindingResult);
            logger.error("bindingResult: " + errorMap);
            return ResponseEntity.badRequest().body(ResponseErrorFieldDto.builder().ok(false).data(errorMap).build());
        }
        return null;
    }

    @Data
    @Builder
    @ToString
    public static class ResponseErrorFieldDto {
        private boolean ok;
        private Map<String, String> data;
    }
}


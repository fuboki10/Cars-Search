package com.sarmad.cars.monitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final ObjectMapper mapper;
    private final LoggingService actionLogService;
    private static final String[] EXCLUDED_FIELDS = {"password"};

    @Around("@annotation(logAction)")
    public Object logAction(ProceedingJoinPoint joinPoint, LogAction logAction) throws Throwable {
        // Capture request data
        String actionName = logAction.value();
        String requestData = extractRequestData(joinPoint);

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        // Capture response data
        String responseData = extractResponseData(result);

        // Capture username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Save to database
        actionLogService.saveLog(actionName, requestData, responseData, username);

        return result;
    }

    private String extractResponseData(Object result) throws JsonProcessingException {
        if (result instanceof ResponseEntity) {
            return mapper.writeValueAsString(((ResponseEntity<?>) result).getBody());
        }
        return result.toString();
    }

    private String extractRequestData(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        HashMap<String, Object> map = new HashMap<>();

        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        String req = mapper.writeValueAsString(map);

        // Mask excluded fields
        for (String field : EXCLUDED_FIELDS) {
            req = req.replaceAll("\"" + field + "\"\\s*:\\s*\"[^\"]*\"", "\"" + field + "\":\"********\"");
        }

        return req;
    }
}

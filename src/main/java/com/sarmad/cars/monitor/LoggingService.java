package com.sarmad.cars.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LoggingRepository loggingRepository;

    public void saveLog(String actionName, String requestData, String responseData, String username) {
        LoggingModel loggingModel = LoggingModel.builder()
                .actionName(actionName)
                .requestData(requestData)
                .responseData(responseData)
                .username(username)
                .build();
        loggingRepository.save(loggingModel);
    }

    public List<LoggingModel> getLogsByActionName(String actionName) {
        return loggingRepository.findByActionName(actionName);
    }

    public List<LoggingModel> getLogsByUsername(String username) {
        return loggingRepository.findByUsername(username);
    }
}

package com.sarmad.cars.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monitor")
@RequiredArgsConstructor
public class LoggingController {

    private final LoggingService loggingService;

    @GetMapping("/actions")
    public ResponseEntity<List<LoggingModel>> getActionLogs(@RequestParam(value = "action", required = true) String action) {
        return ResponseEntity.ok(this.loggingService.getLogsByActionName(action));
    }

    @GetMapping("/users")
    public ResponseEntity<List<LoggingModel>> getUserLogs(@RequestParam(value = "username", required = true) String username) {
        return ResponseEntity.ok(this.loggingService.getLogsByUsername(username));
    }
}

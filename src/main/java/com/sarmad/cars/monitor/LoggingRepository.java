package com.sarmad.cars.monitor;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoggingRepository extends MongoRepository<LoggingModel, String> {
    List<LoggingModel> findByActionName(String actionName);

    List<LoggingModel> findByUsername(String username);
}

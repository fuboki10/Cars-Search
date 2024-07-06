package com.sarmad.cars.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarModelRepository extends MongoRepository<CarModel, String> {
}

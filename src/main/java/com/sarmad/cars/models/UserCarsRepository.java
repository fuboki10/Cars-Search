package com.sarmad.cars.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCarsRepository extends MongoRepository<UserCars, String> {
}

package com.sarmad.cars.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCarsRepository extends MongoRepository<UserCar, String> {
    List<UserCar> findByUserIdIn(List<String> list);

    List<UserCar> findByUserIdInAndCarPlateNumber(List<String> list, String carPlateNumber);
}

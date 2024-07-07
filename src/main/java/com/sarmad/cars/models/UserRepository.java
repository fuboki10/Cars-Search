package com.sarmad.cars.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByLoginId(String loginId);

    List<User> findByFirstNameAndLastName(String userFirstName, String userLastName);
}

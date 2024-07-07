package com.sarmad.cars.cars;


import com.sarmad.cars.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCarsService {

    private final UserRepository userRepository;
    private final UserCarsRepository userCarsRepository;
    private final CarModelService carModelService;

    public List<UserCarsSearchResponse> search(UserCarsSearchRequest searchRequest) {
        System.out.println("Searching for user cars with search request: " + searchRequest.toString());

        final List<UserCar> userCars = this.getUserCars(searchRequest);

        System.out.println("User cars: " + userCars);

        List<String> carModelIds = userCars.stream().map(UserCar::getCarModelId).toList();

        // fetch car models with car model ids
        var carModels = this.carModelService.getCarModelsByIds(carModelIds);
        var carModelsMap = carModels.stream().collect(Collectors.toMap(CarModel::getId, Function.identity()));

        // append car models to user cars
        return userCars.stream().map(userCar -> {
            var carModel = carModelsMap.get(userCar.getCarModelId());

            return UserCarsSearchResponse.builder()
                    .id(userCar.getId())
                    .carPlateNumber(userCar.getCarPlateNumber())
                    .color(userCar.getColor())
                    .carModel(carModel)
                    .build();
        }).toList();
    }

    private List<UserCar> getUserCars(UserCarsSearchRequest searchRequest) {
        // fetch user cars from database with first name and last name
        var users = this.userRepository.findByFirstNameAndLastName(searchRequest.getUserFirstName(), searchRequest.getUserLastName());
        List<String> userIds = users.stream().map(User::getId).toList();

        final List<UserCar> userCars;

        if (searchRequest.getCarPlateNumber() != null) {
            // fetch user cars with user id in users and car plate number
            userCars = this.userCarsRepository.
                    findByUserIdInAndCarPlateNumber(userIds, searchRequest.getCarPlateNumber());
        } else {
            // fetch user cars with user id in users
            userCars = this.userCarsRepository.
                    findByUserIdIn(userIds);
        }

        return userCars;
    }
}

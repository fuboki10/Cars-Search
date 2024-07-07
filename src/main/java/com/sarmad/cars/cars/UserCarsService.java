package com.sarmad.cars.cars;


import com.sarmad.cars.models.CarModel;
import com.sarmad.cars.models.UserCar;
import com.sarmad.cars.models.User;
import com.sarmad.cars.models.UserCarsRepository;
import com.sarmad.cars.models.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCarsService {

    private final UserRepository userRepository;
    private final UserCarsRepository userCarsRepository;
    private final CarModelService carModelService;

    public List<UserCarsSearchResponse> search(UserCarsSearchRequest searchRequest) {
        System.out.println("Searching for user cars with search request: " + searchRequest.toString());

        final List<UserCar> userCars = this.getUserCars(searchRequest);

        // append car models to user cars
        return userCars.stream().map(userCar -> {
            CarModel carModel = carModelService.getCarModel(userCar.getCarModelId());

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

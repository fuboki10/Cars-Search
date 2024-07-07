package com.sarmad.cars.cars;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/cars")
@RequiredArgsConstructor
public class UserCarsController {

    private final UserCarsService userCarsService;

    @GetMapping()
    public ResponseEntity<List<UserCarsSearchResponse>> getUserCars(
            @RequestParam(name = "first_name", required = true) String userFirstName,
            @RequestParam(name = "last_name", required = true) String userLastName,
            @RequestParam(name = "car_plate_number", required = false) String carPlateNumber
    ) {
        UserCarsSearchRequest searchRequest = UserCarsSearchRequest.builder()
                .userFirstName(userFirstName)
                .userLastName(userLastName)
                .carPlateNumber(carPlateNumber)
                .build();
        return ResponseEntity.ok(this.userCarsService.search(searchRequest));
    }

}

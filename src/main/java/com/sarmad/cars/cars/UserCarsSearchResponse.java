package com.sarmad.cars.cars;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sarmad.cars.models.CarModel;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCarsSearchResponse {

    private String id;

    @JsonProperty("car_plate_number")
    private String carPlateNumber;

    private String color;

    @JsonProperty("car_model")
    private CarModel carModel;
}

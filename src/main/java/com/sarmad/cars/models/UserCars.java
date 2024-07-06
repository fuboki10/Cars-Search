package com.sarmad.cars.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "USER_CARS")
public class UserCars {
    @Id
    private String id;

    @Field("USER_ID")
    private String userId;

    @Field("CAR_MODEL_ID")
    private String carModelId;

    @Field("CAR_PLATE_NUMBER")
    private String carPlateNumber;

    @Field("COLOR")
    private String color;
}

package com.sarmad.cars.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_CARS")
public class UserCar {
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

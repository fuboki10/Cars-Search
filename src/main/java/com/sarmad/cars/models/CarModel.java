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
@Document(collection = "CAR_MODELS")
public class CarModel {
    @Id
    private String id;

    @Field("MODEL_NAME")
    private String modelName;

    @Field("TYPE")
    private String type;

    @Field("MANUFACTURER_YEAR")
    private Integer manufacturerYear;
}

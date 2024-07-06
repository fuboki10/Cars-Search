package com.sarmad.cars.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "CAR_MODELS")
public class CarModel {
    @Id
    private String id;

    @Field("CAR_MODEL_ID")
    private String modelId;

    @Field("MODEL_NAME")
    private String modelName;

    @Field("TYPE")
    private String type;

    @Field("MANUFACTURER_YEAR")
    private Integer manufacturerYear;
}

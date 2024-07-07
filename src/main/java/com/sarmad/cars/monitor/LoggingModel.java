package com.sarmad.cars.monitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "LOGGING")
public class LoggingModel {

    @Id
    private String id;

    @Field("ACTION_NAME")
    @Indexed
    private String actionName;

    @Field("REQUEST_DATA")
    private String requestData;

    @Field("RESPONSE_DATA")
    private String responseData;

    @Field("USERNAME")
    @Indexed
    private String username;

    @Field("CREATED_AT")
    @CreatedDate
    private LocalDateTime createdAt;
}

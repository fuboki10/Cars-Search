package com.sarmad.cars.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "USERS")
public class User {
    @Id
    private String id;

    @Field("USER_ID")
    private String userId;

    @Field("LOGIN_ID")
    private String loginId;

    @Field("FIRST_NAME,")
    private String firstName;

    @Field("SECOND_NAME")
    private String lastName;

    @Field("PASSWORD")
    private String password;
}

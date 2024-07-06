package com.sarmad.cars.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USERS")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }
}

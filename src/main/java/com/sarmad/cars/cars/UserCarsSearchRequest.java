package com.sarmad.cars.cars;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCarsSearchRequest {
    @NonNull private String userFirstName;
    @NonNull private String userLastName;
    private String carPlateNumber;
}

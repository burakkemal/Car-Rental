package com.etiya.ReCapProject.business.requests.carDamageRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
    @JsonIgnore
    private int id;

    private String damageDescription;

    private int carId;
}

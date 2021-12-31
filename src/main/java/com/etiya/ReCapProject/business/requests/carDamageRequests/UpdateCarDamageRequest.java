package com.etiya.ReCapProject.business.requests.carDamageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
    private int id;

    private String damageDescription;

    private int carId;
}

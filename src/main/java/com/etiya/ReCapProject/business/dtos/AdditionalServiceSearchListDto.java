package com.etiya.ReCapProject.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceSearchListDto {

    private int id;
    private String serviceName;
    private double dailyPrice;
    private String description;
    private int carId;

}

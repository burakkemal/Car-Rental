package com.etiya.ReCapProject.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageSearchListDto {
    private int id;
    private String damageDescription;
    private int carId;
}

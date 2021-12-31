package com.etiya.ReCapProject.entities.concretes.complexTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetail {

    private int id;
    private String brandName;
    private String colorName;
    private double dailyPrice;
    private String description;
    private String cityName;
    private int kilometer;
}

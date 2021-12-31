package com.etiya.ReCapProject.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalSearchListDto {

    private int id;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private int carId;

    private int customerId;

    private int returnedCityId;

    private int rentedCityId;
}

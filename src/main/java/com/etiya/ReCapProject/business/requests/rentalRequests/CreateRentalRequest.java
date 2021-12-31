package com.etiya.ReCapProject.business.requests.rentalRequests;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.etiya.ReCapProject.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @JsonIgnore
    private int id;

    @NotNull
    private int carId;

    @NotNull
    private int customerId;

    @JsonIgnore
    private LocalDate rentDate;


    @JsonIgnore
    private int rentedCityId;


    @JsonIgnore
    private int rentedKilometer;


}

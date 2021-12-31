package com.etiya.ReCapProject.business.requests.rentalRequests;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

    @NotNull
    private int id;

    @JsonIgnore
    private LocalDate rentDate;

    @FutureOrPresent
    private LocalDate returnDate;

    @NotNull
    private int carId;

    @NotNull
    private int customerId;

    private int returnCityId;

    @JsonIgnore
    private int rentedCityId;

    @JsonIgnore
    private int rentedKilometer;

    private int returnedKilometer;

    private CreatePaymentRequest createPaymentRequest;


}

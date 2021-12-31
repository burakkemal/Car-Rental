package com.etiya.ReCapProject.business.requests.additionalRentalItemRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRentalItemRequest
{
    @JsonIgnore
    private int id;

    private  int additionalServiceId;
    private int rentalId;
}



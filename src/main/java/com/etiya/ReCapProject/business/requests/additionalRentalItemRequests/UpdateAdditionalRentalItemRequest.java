package com.etiya.ReCapProject.business.requests.additionalRentalItemRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalRentalItemRequest {
    private int id;
    private int additionalServiceId;
    private int rentalId;
}

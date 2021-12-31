package com.etiya.ReCapProject.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalRentalItemSearchListDto {
    private int additionalServiceId;
    private int rentalId;
}

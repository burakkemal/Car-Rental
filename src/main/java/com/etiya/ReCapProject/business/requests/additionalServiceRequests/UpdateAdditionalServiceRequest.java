package com.etiya.ReCapProject.business.requests.additionalServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {

    private int id;

    @NotBlank
    @Size(min = 2,max = 30)
    private String serviceName;
    @Min(0)
    private int dailyPrice;

    @NotBlank
    private String description;


}

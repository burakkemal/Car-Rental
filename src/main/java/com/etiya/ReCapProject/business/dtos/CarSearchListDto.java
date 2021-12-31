package com.etiya.ReCapProject.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSearchListDto {
  
  private int id;
  private int modelYear;
  private double dailyPrice;
  private String description;
  private int findexScore;
  private int cityId;
  private int kilometer;
}

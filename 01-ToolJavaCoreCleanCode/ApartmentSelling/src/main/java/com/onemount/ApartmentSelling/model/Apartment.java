package com.onemount.ApartmentSelling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Apartment {
  private int id;
  private String project;
  private String tower;
  private String room;
  private long price;
  
}

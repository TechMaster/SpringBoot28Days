package com.onemount.barcelonateam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
  private String name;
  private int number;
  private Position position;
}

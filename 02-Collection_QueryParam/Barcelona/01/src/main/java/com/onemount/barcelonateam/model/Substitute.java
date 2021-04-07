package com.onemount.barcelonateam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Substitute {
  private Player outPlayer;
  private Player inPlayer;
}

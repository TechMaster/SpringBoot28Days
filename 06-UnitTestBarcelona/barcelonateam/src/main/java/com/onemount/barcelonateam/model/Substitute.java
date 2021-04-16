package com.onemount.barcelonateam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Substitute {
    private Player inPlayer;
    private Player outPlayer;
}

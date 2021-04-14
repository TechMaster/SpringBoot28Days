package com.onemount.barcelonateam.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
  //Lưu thông tin từng cầu thủ
  //Tên
  //Số áo
  //Vị trí


    public Player(String name, int number, Position position) {
        this.name = name;
        this.number = number;
        this.position = position;
    }

    private String name;
    private int number;
    private Position position;
}

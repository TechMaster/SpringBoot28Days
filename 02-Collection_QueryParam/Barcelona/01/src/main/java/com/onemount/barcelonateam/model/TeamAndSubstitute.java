package com.onemount.barcelonateam.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamAndSubstitute {
  private List<Player> currentTeam;  //danh sách cầu thủ mới nhất trên sân
  private List<Substitute> substituteHistory; //Lịch sử các lần thay cầu thủ
}
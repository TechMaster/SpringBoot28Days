package com.onemount.barcelonateam.service;

import java.util.List;

import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
  //Hãy tạo method chọn team
  //Chọn ngẫu nhiên cho từng vị trí
  @Autowired
  private PlayerRepository playerRepository;

  public List<Player> chooseTeam(String teamPattern) {
    
  }
}

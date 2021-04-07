package com.onemount.barcelonateam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.onemount.barcelonateam.exception.TeamException;
import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import com.onemount.barcelonateam.model.Substitute;
import com.onemount.barcelonateam.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
  //Hãy tạo method chọn team
  //Chọn ngẫu nhiên cho từng vị trí
  @Autowired
  private PlayerRepository playerRepository;

  HashSet<Player> currentTeam;
  List<Substitute> substituteHistory;

  public CoachService() {
    currentTeam = new HashSet<>();
    substituteHistory = new ArrayList<>();
  }

  public Set<Player> chooseTeam(int dfNum, int mfNum, int fwNum) {
    if((dfNum +mfNum + fwNum) != 10) {
      throw new IllegalArgumentException("Sum of defenders " + dfNum + " and mid fielders " + mfNum + " and fowarder " + fwNum + " must be 10!");
    }
    
    currentTeam.clear();  //Xoá hết rồi chọn lại
    substituteHistory.clear();

    currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.GK, 1));
    currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.DF, dfNum));
    currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.FW, fwNum));
    currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.MF, mfNum));    
    
    return currentTeam;
  }

  public Set<Player> getCurrentTeam() {
    return currentTeam;
  }

  public List<Substitute> subtitude(int playerno, Position position) throws TeamException{
    if (currentTeam == null) {
      throw new TeamException("Team is not formed yet");
    }

    if (substituteHistory.size() == 5) {
      throw new TeamException("Number of substitution exceeds 5");
    }

    
  }

  public List<Player> availablePlayers() {
    List<Player> available = new ArrayList<Player>();
    for (Player i: playerRepository.getPlayers()) {
      if (!currentTeam.contains(i) && !substituteHistory.contains(i)) {
        available.add(i);
      }
    }
    
    
    return available;
  }


}

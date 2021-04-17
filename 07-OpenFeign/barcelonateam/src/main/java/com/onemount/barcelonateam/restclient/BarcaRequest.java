package com.onemount.barcelonateam.restclient;

import java.util.Set;

import com.onemount.barcelonateam.model.Player;

import feign.Param;
import feign.RequestLine;

public interface BarcaRequest {
  @RequestLine("GET /team")
  Set<Player> getTeam();

  @RequestLine("GET /chooseteam/{team_pattern}")
  Set<Player> chooseTeam(@Param("team_pattern") String team_pattern);
}

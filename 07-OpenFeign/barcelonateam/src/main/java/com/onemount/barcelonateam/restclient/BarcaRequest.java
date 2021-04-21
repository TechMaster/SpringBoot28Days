package com.onemount.barcelonateam.restclient;

import java.util.Map;
import java.util.Set;

import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import com.onemount.barcelonateam.model.TeamStatus;

import feign.Param;
import feign.RequestLine;

public interface BarcaRequest {
  @RequestLine("GET /team")
  Set<Player> getTeam();

  @RequestLine("GET /chooseteam/{team_pattern}")
  Set<Player> chooseTeam(@Param("team_pattern") String team_pattern);

  @RequestLine("GET /substitute/{playerno}/{position}")
  TeamStatus subtitute(@Param("playerno") int playerno, @Param("position") Position position);

  @RequestLine("GET /teamgroup")
  Map<String, Set<Player>> getTeamGroup();
}

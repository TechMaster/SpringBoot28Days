package com.onemount.barcelonateam.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TeamStatus {

    private Set<Player> currentTeam;
    private List<Substitute> substituteHistory;

}

package com.onemount.barcelonateam.model;


import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamStatus {
    private Set<Player> currentTeam;
    private List<Substitute> substituteHistory;
}

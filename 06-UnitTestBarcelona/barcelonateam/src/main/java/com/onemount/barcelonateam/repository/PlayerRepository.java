package com.onemount.barcelonateam.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository {
    //Lưu danh sách các cầu thủ
    private List<Player> players;
    private List<Player> subPlayers;
    private List<Player> playPlayers;

    public PlayerRepository() {

        players = new ArrayList<>();
        players.add(new Player("Marc-André ter Stegen", 1, Position.GK));
        players.add(new Player("Sergiño Dest", 2, Position.DF));
        players.add(new Player("Gerard Piqué", 3, Position.DF));
        players.add(new Player("Ronald Araújo", 4, Position.DF));
        players.add(new Player("Sergio Busquets", 5, Position.MF));
        players.add(new Player("Antoine Griezmann", 6, Position.FW));
        players.add(new Player("Miralem Pjanić", 7, Position.MF));
        players.add(new Player("Martin Braithwaite", 8, Position.FW));
        players.add(new Player("Lionel Messi", 9, Position.FW));
        players.add(new Player("Ousmane Dembélé", 10, Position.FW));
        players.add(new Player("Riqui Puig", 11, Position.MF));
        players.add(new Player("Neto", 12, Position.GK));
        players.add(new Player("Clément Lenglet", 13, Position.DF));
        players.add(new Player("Pedri", 14, Position.MF));
        players.add(new Player("Francisco Trincão", 15, Position.FW));
        players.add(new Player("Jordi Alba", 16, Position.DF));
        players.add(new Player("Matheus Fernandes", 17, Position.MF));
        players.add(new Player("Sergi Roberto", 18, Position.DF));
        players.add(new Player("Frenkie de Jong", 19, Position.MF));
        players.add(new Player("Ansu Fati", 20, Position.FW));
        players.add(new Player("Samuel Umtiti", 21, Position.DF));
        players.add(new Player("Junior Firpo", 22, Position.DF));
    }

    public List<Player> getPlayers(Position position) {
        List<Player> results = new ArrayList<>();
        for (Player p : this.players) {
            if (p.getPosition() == position) {
                results.add(p);
            }
        }
        return results;
    }

    public List<Player> getPlayersUsingStream(Position position) {
        return players
                .stream()
                .filter(p -> p.getPosition() == position)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}

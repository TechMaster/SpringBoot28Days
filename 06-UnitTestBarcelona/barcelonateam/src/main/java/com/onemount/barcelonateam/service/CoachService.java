package com.onemount.barcelonateam.service;

import java.util.*;
import java.util.stream.Collectors;

import com.onemount.barcelonateam.exceptions.TeamException;
import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import com.onemount.barcelonateam.model.Substitute;
import com.onemount.barcelonateam.model.TeamStatus;
import com.onemount.barcelonateam.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
    static final String TEAM_PATTERN = "442";

    //Hãy tạo method chọn team
    //Chọn ngẫu nhiên cho từng vị trí
    @Autowired
    private PlayerRepository playerRepository;

    private Set<Player> currentTeam;
    private List<Substitute> substituteHistory;

    public CoachService() {
        currentTeam = new HashSet<>();
        substituteHistory = new ArrayList<>();
    }

    public Set<Player> getCurrentTeam(){
        if (currentTeam.isEmpty()){
            return chooseTeam();
        }
        return currentTeam;
    }

    public Map<String, Set<Player>> getTeamGroupByPosition() {
        if (currentTeam.isEmpty()){
           chooseTeam();
        }
        HashMap<String, Set<Player>> result = new HashMap<>();
        
        Set<Player> goalKeeper = currentTeam
            .stream()
            .filter(p -> p.getPosition() == Position.GK)
            .collect(Collectors.toSet());

        result.put("GK", goalKeeper);

        Set<Player> defenders = currentTeam
            .stream()
            .filter(p -> p.getPosition() == Position.DF)
            .collect(Collectors.toSet());
        result.put("DF", defenders);

        Set<Player> midfielders = currentTeam
        .stream()
        .filter(p -> p.getPosition() == Position.MF)
        .collect(Collectors.toSet());
        result.put("MF", midfielders);


        Set<Player> forwarders = currentTeam
        .stream()
        .filter(p -> p.getPosition() == Position.FW)
        .collect(Collectors.toSet());
        result.put("FW", forwarders);

        return result;
    }

    public Set<Player> chooseTeam() throws TeamException {        
        return chooseTeam(TEAM_PATTERN);
    }

    // team pattern: 442, 432
    public Set<Player> chooseTeam(String teamPattern){
        currentTeam.clear();
        substituteHistory.clear();
        
        if (teamPattern.length() != 3){
            throw new TeamException("Pattern is invalid", new Throwable(teamPattern));
        }

        int gkNum = 1; // always one goal keeper
        int dfNum = 0;
        int mfNum = 0;
        int fwNum = 0;

        try {
            dfNum = Integer.parseInt(teamPattern.substring(0, 1)); // first char
            mfNum = Integer.parseInt(teamPattern.substring(1, 2)); // second
            fwNum = Integer.parseInt(teamPattern.substring(2, 3)); // third char       
        } catch (NumberFormatException e) {
            throw new TeamException("Invalid number format in team pattern", e);
        }

        if (gkNum + dfNum + mfNum + fwNum != 11) {
            throw new TeamException("Total number of players is not 11", 
            new Throwable(dfNum + " + " + mfNum + " + " + fwNum + " must be 10"));
        }

        List<Player> selectedGk = getPlayers(playerRepository.getPlayers(Position.GK), gkNum);
        List<Player> selectedDf = getPlayers(playerRepository.getPlayers(Position.DF), dfNum);
        List<Player> selectedMf = getPlayers(playerRepository.getPlayers(Position.MF), mfNum);
        List<Player> selectedFw = getPlayers(playerRepository.getPlayers(Position.FW), fwNum);

        currentTeam.addAll(selectedGk);
        currentTeam.addAll(selectedDf);
        currentTeam.addAll(selectedMf);
        currentTeam.addAll(selectedFw);

        return currentTeam;
    }



    private List<Player> getPlayers(List<Player> playerList, int num) {
        if (num > playerList.size())
            throw new TeamException("Request players more than available", new Throwable("Requested: " + num + ", available: " + playerList.size()));
        List<Player> players = new ArrayList<>();
        Random rand = new Random();
        while (num > 0) {
            int randomIndex = rand.nextInt(playerList.size());
            Player p = playerList.get(randomIndex);
            if (!players.contains(p)) {
                players.add(p);
                num--;
            }
        }
        return players;
    }

    

    /**
     * Rule:
     * - được phép thay tối đa 5 lần
     * - Cầu thủ ra sân không được vào sân lại
     * <p>
     * Điều kiện tiên quyết:
     * - Đã chọn xong danh sách cầu thủ
     * <p>
     * Yêu cầu hiển thị:
     * - Danh sách đội hình mới
     * - Danh sách các lần thay đổi cầu thủ
     * <p>
     * Ngoại lệ - lỗi
     * - Thay quá số lần cho phép
     * - Vị trí muốn thay không còn cầu thủ nào
     * - Số áo cầu thủ không tồn tại
     * - Số áo cầu thủ không đá trên sân
     */
    public TeamStatus subtitute(int playerNo, Position position){
        // Đã chọn xong danh sách cầu thủ
        if (currentTeam.size() != 11) {
            throw new TeamException("Team must be made first");
        }

        // check maximum of sub
        if (substituteHistory.size() == 5) {
            throw new TeamException("Number of substitutions has reached 5");
        }

        // check player with playNo is playing
        Player outPlayer = currentTeam.stream()
                .filter(p -> p.getNumber() == playerNo)
                .findAny()
                .orElse(null);

        if (outPlayer == null) {
            throw new TeamException("No current player with that number", new Throwable("" +  playerNo));
        }

        List<Player> availablePlayerForSub = getAvailablePlayers(playerNo, position);

        if (availablePlayerForSub.isEmpty()) {
            throw new TeamException("No player is available for this position", new Throwable(position.toString()));
        }

        Random random = new Random();
        Player inPlayer = availablePlayerForSub.get(random.nextInt(availablePlayerForSub.size()));
        substituteHistory.add(new Substitute(inPlayer, outPlayer));
        currentTeam.remove(outPlayer);
        currentTeam.add(inPlayer);


        return new TeamStatus(currentTeam, substituteHistory);
  
    }

    private List<Player> getAvailablePlayers(int playerNo, Position position) {

        // using stream
        List<Player> subPlayers = substituteHistory.stream()
                .map(Substitute::getOutPlayer)
                .collect(Collectors.toList());

        return playerRepository.getPlayers()
                .stream()
                .filter(p -> p.getPosition() == position
                        && p.getNumber() != playerNo
                        && !currentTeam.contains(p)
                        && !subPlayers.contains(p))
                .collect(Collectors.toList());
    }

}

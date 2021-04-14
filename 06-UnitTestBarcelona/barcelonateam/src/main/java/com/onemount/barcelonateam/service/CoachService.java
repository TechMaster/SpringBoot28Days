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


    // team pattern: 442, 4321
    public Set<Player> chooseTeam(String teamPattern) throws TeamException {
        currentTeam.clear();
        if (teamPattern.length() < 3 || teamPattern.length() > 4)
            throw new TeamException("Pattern is invalid.");

        int gkNum = 1; // always
        int dfNum = Integer.parseInt(teamPattern.substring(0, 1)); // first char
        int mfNum = 1; // second + third
        int fwNum = Integer.parseInt(teamPattern.substring(teamPattern.length() - 1)); // last char

        if (teamPattern.length() == 4) {
            mfNum = Integer.parseInt(teamPattern.substring(1, 2)) + Integer.parseInt(teamPattern.substring(2, 1));
        } else {
            mfNum = Integer.parseInt(teamPattern.substring(1, 2));
        }

        if (gkNum + dfNum + mfNum + fwNum != 11)
            throw new TeamException("Total number of player is not 11");

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

    private int convertToInt(String num){
        return -1;
    }

    private List<Player> getPlayers(List<Player> playerList, int num) {
        if (num > playerList.size())
            throw new IllegalArgumentException("Number of selected player must be smaller than total players");
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

    public Set<Player> chooseTeam() throws TeamException {
        return chooseTeam("442");
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
    public TeamStatus subtitute(int playerNo, Position position) throws TeamException {
        // Đã chọn xong danh sách cầu thủ
        if (currentTeam == null || currentTeam.size() != 11) {
            throw new TeamException("Team must be made first");
        }

        // check maximum of sub
        if (substituteHistory == null || substituteHistory.size() == 5) {
            throw new TeamException("Team must be made first");
        }

        // check player with playNo is playing
        Player outPlayer = currentTeam.stream()
                .filter(p -> p.getNumber() == playerNo)
                .findAny()
                .orElse(null);
        if (outPlayer == null) {
            throw new TeamException("Player with number " + playerNo + " is not playing or invalid");
        }

        List<Player> availablePlayerForSub = getAvailablePlayers(playerNo, position);

        if (availablePlayerForSub.isEmpty()) {
            throw new TeamException("There is no player available for substitute");
        }

        Random random = new Random();
        Player inPlayer = availablePlayerForSub.get(random.nextInt(availablePlayerForSub.size()));
        substituteHistory.add(new Substitute(inPlayer, outPlayer));
        currentTeam.remove(outPlayer);
        currentTeam.add(inPlayer);

        TeamStatus teamsStatus = new TeamStatus();
        teamsStatus.setCurrentTeam(currentTeam);
        teamsStatus.setSubstituteHistory(substituteHistory);
        return teamsStatus;
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

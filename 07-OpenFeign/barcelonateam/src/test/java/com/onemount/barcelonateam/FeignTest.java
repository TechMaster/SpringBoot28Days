package com.onemount.barcelonateam;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.onemount.barcelonateam.exceptions.APIException;
import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import com.onemount.barcelonateam.model.Substitute;
import com.onemount.barcelonateam.model.TeamStatus;
import com.onemount.barcelonateam.restclient.APIErrorDecoder;
import com.onemount.barcelonateam.restclient.BarcaRequest;

import feign.*;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.HttpStatus;


@TestInstance(Lifecycle.PER_CLASS)
public class FeignTest {
  private BarcaRequest barcaClient;

  @BeforeAll
  public void buildFeignClient() {
    final Decoder decoder = new GsonDecoder();
    
    barcaClient = Feign
    .builder()
    .client(new OkHttpClient())
    .decoder(decoder)
    .errorDecoder(new APIErrorDecoder(decoder))
    .target(BarcaRequest.class, "http://localhost:8080");
  }

  @Test
  @Order(1)
  @DisplayName(" .1 GET /team must return 11 players")
  void getTeam() {
    Set<Player> players = barcaClient.getTeam();
    assertThat(players).hasSize(11);

    long goalKeeperCount = players.stream()
    .filter(p -> p.getPosition() == Position.GK)
    .count();
    assertThat(goalKeeperCount).isEqualTo(1);
  }

  @Test
	@DisplayName(" .4 /chooseteam/352")
	@Order(4)
	void chooseTeam352() {
    Set<Player> players = barcaClient.chooseTeam("352");

    long numberDefenders = players.stream()
		.filter(p -> p.getPosition() == Position.DF)
		.count();

		long numberMidFielders = players.stream()
		.filter(p -> p.getPosition() == Position.MF)
		.count();

		long numberForwarders = players.stream()
		.filter(p -> p.getPosition() == Position.FW)
		.count();

		assertThat(numberDefenders).isEqualTo(3);
		assertThat(numberMidFielders).isEqualTo(5);
		assertThat(numberForwarders).isEqualTo(2);
  }

  @Test
  @Order(6)
  @DisplayName(" .6 GET /chooseteam/118")
  void chooseTeam118() {
    try {
      barcaClient.chooseTeam("118");
    } catch (APIException e) {
      assertThat(e.getMessage()).isEqualTo("TeamException : Request players more than available");
    }
  }

  @Test
  @Order(7)
  @DisplayName(" .7 GET getTeamGroup")
  void teamgroup() {
    Map<String, Set<Player>> groupPlayers = barcaClient.getTeamGroup();
    assertThat(groupPlayers).hasSize(4).containsKeys("GK","DF","MF","FW");
    assertThat(groupPlayers.get("GK").size()).isEqualTo(1);
  }


  @Test
 	@DisplayName(" 9. substitute with number that is not in current team")
	@Order(9)
	void subtituteANumerNotInTeam() {		
		Set<Player> players = barcaClient.chooseTeam("442");
		//Chọn ra một số áo không phải của bất kỳ cầu thủ nào trên sân
		Random random = new Random();
		int numberNotInTeam;
		while (true) {
			int aNum = random.nextInt(30);
			long count = players
			.stream()
			.filter(player -> player.getNumber() == aNum)
			.count();
			if (count == 0) {
				numberNotInTeam = aNum;
				break;
			}
		}

		try {
			barcaClient.subtitute(numberNotInTeam,Position.DF);															
		} catch (APIException e) {
			assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(e.getMessage()).isEqualTo("TeamException : No current player with that number");
		}
	}

  private int pickARandomPlayerNoInTeam(List<Player> players) {
		Random random = new Random();
		int index = random.nextInt(players.size());
		return players.get(index).getNumber();
	}

  @Test
 	@DisplayName("12. check if substitute exceed 5 times")
	@Order(12)
	void checkSubstituteHistoryExceed5() {
		Set<Player> players = barcaClient.chooseTeam("442");
		int pickARandomPlayerNumberInTeam = pickARandomPlayerNoInTeam(players.stream().toList());
		TeamStatus teamStatus = null;
    List<TeamStatus> a = new ArrayList<>();

    teamStatus = barcaClient.subtitute(pickARandomPlayerNumberInTeam, Position.DF);
    teamStatus = barcaClient.subtitute(pickARandomPlayerNoInTeam(teamStatus.getCurrentTeam().stream().toList()), Position.DF);
    teamStatus = barcaClient.subtitute(pickARandomPlayerNoInTeam(teamStatus.getCurrentTeam().stream().toList()), Position.FW);
    teamStatus = barcaClient.subtitute(pickARandomPlayerNoInTeam(teamStatus.getCurrentTeam().stream().toList()), Position.MF);
    teamStatus = barcaClient.subtitute(pickARandomPlayerNoInTeam(teamStatus.getCurrentTeam().stream().toList()), Position.DF);
		List<Substitute> subtituteHistory = teamStatus.getSubstituteHistory();
		assertThat(subtituteHistory.size()).isEqualTo(5);
	}

  @Test
  @DisplayName("10. check if substitute history is correct")
 @Order(10)
 void checkSubstituteHistory() {
   Set<Player> players = barcaClient.chooseTeam("442");

   int pickARandomPlayerNumberInTeam = pickARandomPlayerNoInTeam(players.stream().toList());


   TeamStatus teamStatus = barcaClient.subtitute(pickARandomPlayerNumberInTeam,Position.DF);
   
   List<Substitute> subtituteHistory = teamStatus.getSubstituteHistory();
   Player outPlayer = subtituteHistory.get(subtituteHistory.size() - 1).getOutPlayer();
   Player inPlayer = subtituteHistory.get(subtituteHistory.size() - 1).getInPlayer();
   assertThat(outPlayer.getNumber()).isEqualTo(pickARandomPlayerNumberInTeam);
   assertThat(inPlayer.getPosition()).isEqualTo(Position.DF);	
 }


}

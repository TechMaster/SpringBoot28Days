package com.onemount.barcelonateam;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.onemount.barcelonateam.exceptions.APIError;
import com.onemount.barcelonateam.exceptions.APIException;
import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import com.onemount.barcelonateam.model.Substitute;
import com.onemount.barcelonateam.model.TeamStatus;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@TestInstance(Lifecycle.PER_CLASS)
class WebFluxClientTest {
	private WebClient webClient;
	@BeforeAll
	void buildWebClient() {
		webClient = WebClient
			.builder()
			.filter(ExchangeFilterFunction.ofResponseProcessor(this::renderApiErrorResponse))
			.baseUrl("http://localhost:8080")
			.build();		
	}

	private Mono<ClientResponse> renderApiErrorResponse(ClientResponse clientResponse) {
		if(clientResponse.statusCode().isError()){
			return clientResponse
			.bodyToMono(APIError.class)
			.flatMap(apiErrorResponse -> Mono.error(new APIException(clientResponse.statusCode(), 
			apiErrorResponse.message, 
			apiErrorResponse.details)));
		}
		return Mono.just(clientResponse);
	}


	@Test
	@DisplayName(" 1. Team must have 11 players")
	@Order(1)
	void teamMustHave11Players() {
		Mono<Player[]> result = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/team")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Player[].class)
				.log();

		Player[] players = result.block();
    assertThat(players).hasSize(11);
	}

	@Test
	@DisplayName(" 2. Team must have only one goal keeper")
	@Order(2)
	void teamMustHaveOneGoalKeeper() {
		List<Player> players = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/team")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
				.block();
	
		long numberOfGoalKeeper = players.stream()
		.filter(p -> p.getPosition() == Position.GK)
		.count();
    assertThat(numberOfGoalKeeper).isEqualTo(1);
	}

	@Test
	@DisplayName(" 3. Team must have some defenders, mid fielders and forwarders")
	@Order(3)
	void teamMustHaveSomeDF_MF_FW() {
		List<Player> players = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/team")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
				.block();

		long numberDefenders = players.stream()
		.filter(p -> p.getPosition() == Position.DF)
		.count();

		long numberMidFielders = players.stream()
		.filter(p -> p.getPosition() == Position.MF)
		.count();

		long numberForwarders = players.stream()
		.filter(p -> p.getPosition() == Position.FW)
		.count();

		assertThat(numberDefenders).isPositive();
		assertThat(numberMidFielders).isPositive();
		assertThat(numberForwarders).isPositive();
	}

	@Test
	@DisplayName(" 4. /chooseteam/352")
	@Order(4)
	void chooseTeam352() {
		List<Player> players = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/chooseteam/352")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
				.block();

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
	@DisplayName(" 5. /chooseteam/334")
	@Order(5)
	void chooseTeam334() {
		List<Player> players = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/chooseteam/334")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
				.block();

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
		assertThat(numberMidFielders).isEqualTo(3);
		assertThat(numberForwarders).isEqualTo(4);
	}

	@Test
	@DisplayName(" 6. /chooseteam/118 should return 400")
	@Order(6)
	void chooseTeam118() {		
		try {
			webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/chooseteam/118")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
				.block();																
		} catch (APIException e) {
			assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(e.getMessage()).isEqualTo("TeamException : Request players more than available");
		}
	}

	@Test
	@DisplayName(" 7. /chooseteam/000 should return 400")
	@Order(7)
	void chooseTeam000() {		
		try {
			webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/chooseteam/000")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
				.block();																
		} catch (APIException e) {
			assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(e.getMessage()).isEqualTo("TeamException : Total number of players is not 11");
		}
	}

	@Test
	@DisplayName(" 8. /chooseteam/345 should return 400")
	@Order(8)
	void chooseTeam345() {		
		try {
			webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/chooseteam/345")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
				.block();																
		} catch (APIException e) {
			assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(e.getMessage()).isEqualTo("TeamException : Total number of players is not 11");
		}
	}

	private List<Player> chooseTeam(String pattern) {
		List<Player> players = webClient.get()
		.uri(uriBuilder -> uriBuilder
				.path("/chooseteam/" + pattern)
				.build())
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(new ParameterizedTypeReference<List<Player>>() {})
		.block();								

		assertThat(players).hasSize(11);
		return players;
	}

	private int pickARandomPlayerNoInTeam(List<Player> players) {
		Random random = new Random();
		int index = random.nextInt(players.size());
		return players.get(index).getNumber();
	}


	@Test
 	@DisplayName(" 9. substitute with number that is not in current team")
	@Order(9)
	void subtituteANumerNotInTeam() {		
		List<Player> players = chooseTeam("442");
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
			webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/substitute/" + numberNotInTeam + "/DF")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<TeamStatus>() {})
				.block();																
		} catch (APIException e) {
			assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(e.getMessage()).isEqualTo("TeamException : No current player with that number");
		}
	}

	@Test
 	@DisplayName("10. check if substitute history is correct")
	@Order(10)
	void checkSubstituteHistory() {
		List<Player> players = chooseTeam("442");

		int pickARandomPlayerNumberInTeam = pickARandomPlayerNoInTeam(players);

	
		TeamStatus teamStatus = webClient.get()
			.uri(uriBuilder -> uriBuilder
					.path("/substitute/" + pickARandomPlayerNumberInTeam + "/DF")
					.build())
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<TeamStatus>() {})
			.block();
		
		List<Substitute> subtituteHistory = teamStatus.getSubstituteHistory();
		Player outPlayer = subtituteHistory.get(subtituteHistory.size() - 1).getOutPlayer();
		Player inPlayer = subtituteHistory.get(subtituteHistory.size() - 1).getInPlayer();

		assertThat(outPlayer.getNumber()).isEqualTo(pickARandomPlayerNumberInTeam);
		assertThat(inPlayer.getPosition()).isEqualTo(Position.DF);	
	}

	@Test
 	@DisplayName("11. get/teamgroup")
	@Order(11)
	void getTeamGroup() {
		Mono<Map<String, Set<Player>>> result = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/teamgroup")
            .build())
				.accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, Set<Player>>>() {})
				.log();

		Map<String, Set<Player>> groupPlayers = result.block();
    assertThat(groupPlayers)
			.hasSize(4)
			.containsKeys("GK", "DF", "MF", "FW");

		assertThat(groupPlayers.get("GK").size()).isEqualTo(1); //Vị trí thủ môn chỉ có một
	}
}

package com.onemount.barcelonateam;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import com.onemount.barcelonateam.exceptions.APIException;
import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
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
}

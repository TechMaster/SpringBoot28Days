package vn.techmaster.testrest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import static org.assertj.core.api.Assertions.assertThat;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class TestrestApplicationTests {

	@Test
	void connect8080() {
		WebClient webClient = WebClient.create("http://localhost:8080");
		Mono<String> result = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/team")
            .build())
        .retrieve()
        .bodyToMono(String.class);

    assertThat(result)
	}

}

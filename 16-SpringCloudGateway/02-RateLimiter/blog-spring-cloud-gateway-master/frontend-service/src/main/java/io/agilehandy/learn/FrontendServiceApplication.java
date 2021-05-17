package io.agilehandy.learn;

import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FrontendServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendServiceApplication.class, args);
	}

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl("http://localhost:9090").build();
	}

}

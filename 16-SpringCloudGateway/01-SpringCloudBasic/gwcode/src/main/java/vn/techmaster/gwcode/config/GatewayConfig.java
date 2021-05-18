package vn.techmaster.gwcode.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayConfig {
  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
  }
  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
  return builder.routes()
        .route(r -> r.path("/book/**").uri("http://localhost:8081/"))
        .route(r -> r
        .path("/film/**")
        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()).setStatusCode(HttpStatus.TOO_MANY_REQUESTS)))
        .uri("http://localhost:8082/"))
        .build();
  }

  @Bean
  public RedisRateLimiter redisRateLimiter() {
    return new RedisRateLimiter(100, 300);
  }
}
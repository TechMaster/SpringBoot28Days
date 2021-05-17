https://springframework.guru/api-gateway/

https://medium.com/@niral22/spring-cloud-gateway-tutorial-5311ddd59816


https://www.baeldung.com/spring-rest-with-zuul-proxy

https://www.javainuse.com/spring/cloud-gateway


https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.0.2.RELEASE/single/spring-cloud-gateway.html#_requestratelimiter_gatewayfilter_factory
Cần phải bổ xung
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
    <version>2.4.5</version>
</dependency>

            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 1
                redis-rate-limiter.burstCapacity: 2
                redis-rate-limiter.requestedTokens: 1
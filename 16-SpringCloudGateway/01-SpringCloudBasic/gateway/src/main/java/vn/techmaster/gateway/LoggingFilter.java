package vn.techmaster.gateway;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

import java.net.URI;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {
    Log log = LogFactory.getLog(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        Set<URI> originalUris  = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        if (originalUris != null) {
            URI originalUri = originalUris.iterator().next();

            Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);        

            URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
            log.info("Incoming request " + originalUri.toString() + " is routed to id: " + route.getId()
                    + ", uri:" + routeUri);
        }
        
        return chain.filter(exchange);
    }
}
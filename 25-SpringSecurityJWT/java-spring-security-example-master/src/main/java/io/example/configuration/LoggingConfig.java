package io.example.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggingConfig {

    @Bean @Scope("prototype")
    public Logger logger(final InjectionPoint injectionPoint) {
        if (injectionPoint.getMethodParameter() != null) {
            return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
        }

        if (injectionPoint.getField() != null) {
            return LoggerFactory.getLogger(injectionPoint.getField().getDeclaringClass());
        }

        throw new IllegalArgumentException();
    }

}

package cl.esperanza.incidencia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient reparacionesWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8088/api/v1/reparaciones").build();
    }
}
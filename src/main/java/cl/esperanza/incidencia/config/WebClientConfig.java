package cl.esperanza.incidencia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient reparacionesWebClient(@Value("${reparaciones.service.url:http://localhost:8088/api/v1/reparaciones}") String reparacionServiceUrl) {
        return WebClient.builder().baseUrl(reparacionServiceUrl).build();
    }

    @Bean
    public WebClient sociosWebClient(@Value("${socios.service.url:http://localhost:8082/api/v1/socios}") String sociosServiceUrl) {
        return WebClient.builder().baseUrl(sociosServiceUrl).build();
    }
}

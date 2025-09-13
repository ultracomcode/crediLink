package com.main.CrediLink.ixc.client;

import feign.RequestInterceptor;
import feign.Response;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Configuration
public class MeuFeignConfig {

    private final IntegrationService integrationService;

    public MeuFeignConfig(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            String username = "108";
            String password = "78f77d79a8f711c036ac9cdf9bbff5f6348e4bea9a13f837c7fe1ff011ab9505";

            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

            requestTemplate.header("Authorization", "Basic " + encodedAuth);
        };
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder() {
            @Override
            public Object decode(Response response, Type type) throws IOException {
                Collection<String> contentTypeCollection = response.headers()
                        .getOrDefault("Content-Type", Collections.emptyList());

                // Converte Collection<String> para List<String>
                List<String> contentType = new ArrayList<>(contentTypeCollection);

                if (contentType.stream().anyMatch(ct -> ct.contains("text/x-json"))) {
                    response = response.toBuilder()
                            .headers(Collections.singletonMap(
                                    "Content-Type",
                                    Collections.singletonList("application/json")
                            ))
                            .build();
                }
                return super.decode(response, type);
            }
        };
    }

}


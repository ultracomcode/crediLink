package com.main.CrediLink.ixc.client;

import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import com.main.CrediLink.domain.integrations.service.IntegrationService;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class MeuFeignConfig {

    private final IntegrationService integrationService;

    public MeuFeignConfig(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            IntegrationEntity integration = integrationService.findByTypeAndStatus(IntegrationsType.IXC);

            String auth = integration.getTokenApi();
            String encodedAuth = Base64.getEncoder()
                    .encodeToString(auth.getBytes(StandardCharsets.UTF_8));

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


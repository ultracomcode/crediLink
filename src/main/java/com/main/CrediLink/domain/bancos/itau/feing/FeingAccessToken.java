package com.main.CrediLink.domain.bancos.itau.feing;

import com.main.CrediLink.domain.bancos.itau.config.CertificateConfig;
import com.main.CrediLink.domain.bancos.dtos.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.MultiValueMap;
import org.springframework.http.MediaType;

@FeignClient(name = "itauTokenClient",
        url = "${itau.api-url-access-token}",
        configuration = CertificateConfig.class)
public interface FeingAccessToken {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResponse getAccessToken(
            @RequestHeader("x-itau-flowID") String flowId,
            @RequestHeader("x-itau-correlationID") String correlationId,
            @RequestBody MultiValueMap<String, String> body
    );


}

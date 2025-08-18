package com.main.CrediLink.itau.feing;

import com.main.CrediLink.itau.config.CertificateConfig;
import com.main.CrediLink.itau.dto.FetchTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.MultiValueMap;
import org.springframework.http.MediaType;

@FeignClient(name = "itauTokenClient",
        url = "${itau.api.access-token}",
        configuration = CertificateConfig.class)
public interface ItauTokenClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    FetchTokenResponse getToken(
            @RequestHeader("x-itau-flowID") String flowId,
            @RequestHeader("x-itau-correlationID") String correlationId,
            @RequestBody MultiValueMap<String, String> body
    );

}

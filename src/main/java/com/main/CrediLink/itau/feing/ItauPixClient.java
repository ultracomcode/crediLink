package com.main.CrediLink.itau.feing;

import com.main.CrediLink.itau.dto.PixRequest;
import com.main.CrediLink.itau.dto.responsePix.createPixPaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "itauPixClient", url = "${itau.api.base-url}")
public interface ItauPixClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    createPixPaymentDTO gerarPix(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("x-itau-apikey") String apiKey,
            @RequestHeader("x-itau-correlationID") String correlationId,
            @RequestHeader("x-itau-flowID") String flowId,
            @RequestBody PixRequest pixRequest
    );
}

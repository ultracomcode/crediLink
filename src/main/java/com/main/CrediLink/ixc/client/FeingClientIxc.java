package com.main.CrediLink.ixc.client;

import com.main.CrediLink.ixc.dto.generate_finacial.GenerateFinancialRequest;
import com.main.CrediLink.dtos.ResponseDTO;
import com.main.CrediLink.ixc.dto.additional_service.ResponseServiceAdditional;
import com.main.CrediLink.ixc.dto.additional_service.RequestAdditionalService;
import com.main.CrediLink.ixc.dto.IxcResponseDTO;
import com.main.CrediLink.ixc.dto.receive_invoice.InvoiceRequestDTO;
import com.main.CrediLink.ixc.dto.receive_invoice.ReceiveInvoiceRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "ClientIxcAPI",
        url = "${ixc.api.base-url}",
        configuration = MeuFeignConfig.class
)
public interface FeingClientIxc{

    @PostMapping(path = "/cliente_contrato_servicos",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseServiceAdditional createAdditionalService(
            @RequestBody RequestAdditionalService requestAdditionalService
    );

    @PostMapping(path = "/gerar_financeiro_contrato_15314",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseDTO btnGenerateFinancial(
            @RequestBody GenerateFinancialRequest generateFinancialRequest
    );

    @PostMapping(path = "/fn_areceber",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    IxcResponseDTO getReceiveInvoice(
            @RequestHeader("ixcsoft") String ixcsoft,
            @RequestBody InvoiceRequestDTO invoiceRequestDTO
    );

    @PostMapping(path = "/fn_areceber_recebimentos_baixas_novo",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseDTO receiveInvoice(
            @RequestBody ReceiveInvoiceRequestDTO receiveInvoiceRequestDTO
    );

    @PostMapping(path = "/produtos")
    IxcResponseDTO getProductsById(
            @RequestHeader("ixcsoft") String ixcsoft,
            @RequestBody InvoiceRequestDTO invoiceRequestDTO
    );
}

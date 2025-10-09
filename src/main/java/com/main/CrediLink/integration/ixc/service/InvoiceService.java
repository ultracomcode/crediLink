package com.main.CrediLink.integration.ixc.service;

import com.main.CrediLink.application.integration.enums.IntegrationsType;
import com.main.CrediLink.application.integration.service.IntegrationService;
import com.main.CrediLink.application.pix.entity.PixTransactionEntity;
import com.main.CrediLink.application.pix.exceptions.PixException;
import com.main.CrediLink.shared.dtos.ResponseDTO;
import com.main.CrediLink.integration.ixc.client.FeingClientIxc;
import com.main.CrediLink.integration.ixc.dto.IxcMinimalDTO;
import com.main.CrediLink.integration.ixc.dto.IxcResponseDTO;
import com.main.CrediLink.integration.ixc.dto.additional_service.RequestAdditionalService;
import com.main.CrediLink.integration.ixc.dto.additional_service.ResponseServiceAdditional;
import com.main.CrediLink.integration.ixc.dto.generate_finacial.GenerateFinancialRequest;
import com.main.CrediLink.integration.ixc.dto.receive_invoice.InvoiceRequestDTO;
import com.main.CrediLink.integration.ixc.dto.receive_invoice.ReceiveInvoiceRequestDTO;
import com.main.CrediLink.shared.utils.FormatterUtils;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class InvoiceService {

    private static final Pattern ID_SAIDA_PATTERN = Pattern.compile("IDs da saída:\\s*(\\d+)");

    private final FeingClientIxc feingClientIxc;
    private final IntegrationService integrationService;


    public InvoiceService(FeingClientIxc feingClientIxc, IntegrationService integrationService) {
        this.feingClientIxc = feingClientIxc;
        this.integrationService = integrationService;
    }


    public ResponseDTO receiveInvoice(PixTransactionEntity pixTransactionEntity) {
        ResponseDTO financialResponse = btnGenerateFinancial(pixTransactionEntity);

        if (!"success".equals(financialResponse.type())) {
            throw new PixException("Não foi possível gerar cobrança financeira: " + financialResponse.message());
        }

        var invoiceResponse = getInvoiceBySalesId(financialResponse.message());

        if (invoiceResponse.registros().isEmpty()) {
            throw new PixException("Nenhum registro encontrado para a venda: " + financialResponse.message());
        }

        var invoice = invoiceResponse.registros().get(0);

        var request = new ReceiveInvoiceRequestDTO(
                "1",
                invoice.id(),
                "7",
                "1807",
                "Pix",
                FormatterUtils.formatInstantToDate(pixTransactionEntity.getPaymentAt()),
                pixTransactionEntity.getValor(),
                pixTransactionEntity.getValor(),
                pixTransactionEntity.getValor(),
                pixTransactionEntity.getObservacao(),
                "R"
        );

        var response = feingClientIxc.receiveInvoice(request);

        return response;
    }

    private IxcResponseDTO getInvoiceBySalesId(String idSaida){
        var request = new InvoiceRequestDTO(
                "fn_areceber.id_saida",
                idSaida,
                "=",
                "1"
        );

        var response = feingClientIxc.getReceiveInvoice("listar",request);

        return response;
    }

    private ResponseDTO btnGenerateFinancial(PixTransactionEntity pixTransactionEntity){

        additionalService(pixTransactionEntity);

        var request = new GenerateFinancialRequest(
                pixTransactionEntity.getUser().getIdContrato(),
                pixTransactionEntity.getUser().getIdCrm()
        );

        var response = feingClientIxc.btnGenerateFinancial(request);

        Matcher matcher = ID_SAIDA_PATTERN.matcher(response.message());

        if (matcher.find()) {
            return new ResponseDTO("success", matcher.group(1));
        } else {
            return response;
        }
    }

    private ResponseServiceAdditional additionalService(PixTransactionEntity pixTransactionEntity){

        var serviceAdditional = new RequestAdditionalService(
                "T",
                getMinimalDataIntegration().id(),
                getMinimalDataIntegration().descricao(),
                getMinimalDataIntegration().unidade(),
                "1",
                pixTransactionEntity.getValor(),
                pixTransactionEntity.getValor(),
                "V",
                "1",
                pixTransactionEntity.getPaymentAt().toString(),
                "A",
                "A",
                pixTransactionEntity.getUser().getIdContrato(),
                "A"
        );


        var response = feingClientIxc.createAdditionalService(serviceAdditional);

        return response;
    }

    private IxcResponseDTO getProductsById(String idProduto){
        var request = new InvoiceRequestDTO(
                "produtos.id",
                idProduto,
                "=",
                "1"
        );
        var response = feingClientIxc.getProductsById("listar",request);

        return response;
    }

    private IxcMinimalDTO getMinimalDataIntegration(){
        return getProductsById(integrationService.findByTypeAndStatus(IntegrationsType.IXC).getIdProduto())
                .registros().get(0);
    }
}

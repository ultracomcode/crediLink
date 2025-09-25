package com.main.CrediLink.ixc.service;

import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import com.main.CrediLink.domain.integrations.service.IntegrationService;
import com.main.CrediLink.domain.pix.PixTransactionEntity;
import com.main.CrediLink.dtos.ResponseDTO;
import com.main.CrediLink.ixc.client.FeingClientIxc;
import com.main.CrediLink.ixc.dto.IxcMinimalDTO;
import com.main.CrediLink.ixc.dto.IxcResponseDTO;
import com.main.CrediLink.ixc.dto.additional_service.RequestAdditionalService;
import com.main.CrediLink.ixc.dto.additional_service.ResponseServiceAdditional;
import com.main.CrediLink.ixc.dto.generate_finacial.GenerateFinancialRequest;
import com.main.CrediLink.ixc.dto.receive_invoice.InvoiceRequestDTO;
import com.main.CrediLink.ixc.dto.receive_invoice.ReceiveInvoiceRequestDTO;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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


    public ResponseDTO ReceiveInvoice(PixTransactionEntity pixTransactionEntity){

        var invoice = getInvoiceBySalesId(btnGenerateFinancial(pixTransactionEntity));

//        var request = new ReceiveInvoiceRequestDTO(
//                "1",
//                invoice,
//
//
//        )

//        var response = feingClientIxc.receiveInvoice(request);
//
//        return response;

        return new ResponseDTO("success","OK");
    }

    public IxcResponseDTO getInvoiceBySalesId(String idSaida){
        var request = new InvoiceRequestDTO(
                "fn_areceber.id_saida",
                idSaida,
                "=",
                "1"
        );

        var response = feingClientIxc.getReceiveInvoice("listar",request);

        return response;
    }

    public String btnGenerateFinancial(PixTransactionEntity pixTransactionEntity){

        if (additionalService(pixTransactionEntity).type().equals("success")){

            var request = new GenerateFinancialRequest(
                    pixTransactionEntity.getUser().getIdContrato(),
                    pixTransactionEntity.getUser().getIdCrm()
            );

            var response = feingClientIxc.btnGenerateFinancial(request);

            Matcher idSaida = ID_SAIDA_PATTERN.matcher(response.message());

            if(idSaida.find()){
                return idSaida.group(1);
            }
            return response.message();
        }

        return "";
    }

    public ResponseServiceAdditional additionalService(PixTransactionEntity pixTransactionEntity){

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
                pixTransactionEntity.getPaymentAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "A",
                "A",
                pixTransactionEntity.getUser().getIdContrato(),
                "A"
        );


        var response = feingClientIxc.createAdditionalService(serviceAdditional);

        return response;
    }

    public IxcResponseDTO getProductsById(String idProduto){
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
        return getProductsById(integrationService.findByTypeAndStatus(IntegrationsType.IXC).getIdPproduto())
                .registros().get(0);
    }
}

package com.main.CrediLink.ixc.service;

import com.main.CrediLink.ixc.client.FeingClientIxc;
import com.main.CrediLink.ixc.dto.ResponseDTO;
import com.main.CrediLink.ixc.dto.additional_service.RequestAdditionalService;
import com.main.CrediLink.ixc.dto.additional_service.ResponseServiceAdditional;
import com.main.CrediLink.ixc.dto.generate_finacial.GenerateFinancialRequest;
import com.main.CrediLink.ixc.dto.receive_invoice.InvoiceRequestDTO;
import com.main.CrediLink.ixc.dto.IxcResponseDTO;
import com.main.CrediLink.ixc.dto.receive_invoice.ReceiveInvoiceRequestDTO;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InvoiceService {

    private static final Pattern ID_SAIDA_PATTERN = Pattern.compile("IDs da saída:\\s*(\\d+)");

    private final FeingClientIxc feingClientIxc;

    public InvoiceService(FeingClientIxc feingClientIxc) {
        this.feingClientIxc = feingClientIxc;
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

    public String generateFinancial(GenerateFinancialRequest request){
        var response = feingClientIxc.btnGenerateFinancial(request);

        Matcher idSaida = ID_SAIDA_PATTERN.matcher(response.message());

        if(idSaida.find()){
            return idSaida.group(1);
        }
        return response.message();
    }

    public ResponseServiceAdditional additionalService(RequestAdditionalService request){
        var response = feingClientIxc.createAdditionalService(request);

        return response;
    }

    public ResponseDTO ReceiveInvoice(ReceiveInvoiceRequestDTO request){
        var response = feingClientIxc.receiveInvoice(request);

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
}

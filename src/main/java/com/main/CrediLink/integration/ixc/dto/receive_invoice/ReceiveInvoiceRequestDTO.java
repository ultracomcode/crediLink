package com.main.CrediLink.integration.ixc.dto.receive_invoice;

public record ReceiveInvoiceRequestDTO(
        String filial_id,
        String id_receber,
        String conta_,
        String id_conta,
        String tipo_recebimento,
        String data,
        String valor_parcela,
        String credito,
        String valor_total_recebido,
        String historico,
        String tipo_lanc
) {
}

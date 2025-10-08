package com.main.CrediLink.integration.ixc.dto.receive_invoice;

public record InvoiceRequestDTO(
        String qtype,
        String query,
        String oper,
        String rp

) {
}

package com.main.CrediLink.ixc.dto.receive_invoice;

public record InvoiceRequestDTO(
        String qtype,
        String query,
        String oper,
        String rp

) {
}

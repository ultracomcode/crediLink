package com.main.CrediLink.ixc.dto.additional_service;

public record RequestAdditionalService(
        String tipo,
        String id_produto,
        String descricao,
        String id_unidade,
        String quantidade,
        String valor_unitario,
        String valor_total,
        String repetir,
        String repetir_qtde,
        String data,
        String status,
        String status_nf21,
        String id_contrato,
        String tipo_acres_desc
) {}

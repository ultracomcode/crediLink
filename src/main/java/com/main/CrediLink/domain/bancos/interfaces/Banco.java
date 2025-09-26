package com.main.CrediLink.domain.bancos.interfaces;

import com.main.CrediLink.domain.bancos.dtos.PixPaymentRequest;
import com.main.CrediLink.domain.bancos.dtos.PixPaymentResponse;
import com.main.CrediLink.domain.bancos.entity.TokenEntity;

public interface Banco {

    TokenEntity getAccessToken();

    PixPaymentResponse createCharge(PixPaymentRequest pixPaymentRequest);
}

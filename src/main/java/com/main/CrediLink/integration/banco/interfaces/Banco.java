package com.main.CrediLink.integration.banco.interfaces;

import com.main.CrediLink.integration.banco.dto.PixPaymentRequest;
import com.main.CrediLink.integration.banco.dto.PixPaymentResponse;
import com.main.CrediLink.application.token.entity.TokenEntity;

public interface Banco {

    TokenEntity getAccessToken();

    PixPaymentResponse createCharge(PixPaymentRequest pixPaymentRequest);
}

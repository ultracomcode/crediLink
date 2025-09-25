package com.main.CrediLink.domain.bancos.interfaces;

import com.main.CrediLink.domain.bancos.dtos.AccessTokenResponse;
import com.main.CrediLink.domain.bancos.dtos.PixPaymentRequest;
import com.main.CrediLink.domain.bancos.dtos.PixPaymentResponse;
import com.main.CrediLink.domain.bancos.itau.entity.TokenEntityItau;

public interface Banco {

    TokenEntityItau getAccessToken();

    PixPaymentResponse createCharge(PixPaymentRequest pixPaymentRequest);
}

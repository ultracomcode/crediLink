package com.main.CrediLink.application.pix.dto;

import com.main.CrediLink.shared.enuns.PixStatus;

public record ResponsePixStatus(
        PixStatus status,
        String amount
) {
}

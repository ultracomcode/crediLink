package com.main.CrediLink.integration.ixc.dto;

import java.util.List;

public record IxcResponseDTO(
        List<IxcMinimalDTO> registros
) {}


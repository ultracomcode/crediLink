package com.main.CrediLink.shared.enuns;

public enum PixStatus {

    AT("ATIVO", false),
    CA("CANCELADO", true),
    CO("CONCLUIDO", true),
    EX("EXPIRADO", true),
    ER("ERRO", true);

    private final String descricao;
    private final boolean finalizado;

    PixStatus(String descricao, boolean finalizado) {
        this.descricao = descricao;
        this.finalizado = finalizado;
    }

    public String getDescription() {
        return descricao;
    }

    public boolean canBeCancelled() {
        return this == AT;
    }
}


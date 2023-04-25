package br.com.duxus.desafio.dto.dtoComposicaoTime;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroComposicaoTime(
        @NotNull
        Long idTime,

        @NotNull
        Long idIntegrante
) {
}

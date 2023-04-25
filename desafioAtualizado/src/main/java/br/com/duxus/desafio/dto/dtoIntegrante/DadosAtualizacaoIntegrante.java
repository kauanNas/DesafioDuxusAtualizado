package br.com.duxus.desafio.dto.dtoIntegrante;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoIntegrante(
    @NotNull
    Long id,
    String franquia,
    String nome,
    String funcao
) {
}

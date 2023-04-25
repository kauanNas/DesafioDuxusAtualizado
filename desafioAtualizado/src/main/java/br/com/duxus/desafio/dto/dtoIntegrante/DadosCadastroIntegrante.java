package br.com.duxus.desafio.dto.dtoIntegrante;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroIntegrante(
    @NotBlank
    String franquia,

    @NotBlank
    String nome,

    @NotBlank
    String funcao
) {
}

package br.com.duxus.desafio.dto.dtoTime;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoTime(
        @NotNull
        Long id,
        @NotNull
        LocalDate data
) {

}

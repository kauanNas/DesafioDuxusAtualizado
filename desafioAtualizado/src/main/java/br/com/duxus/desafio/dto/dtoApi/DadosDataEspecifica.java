package br.com.duxus.desafio.dto.dtoApi;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record DadosDataEspecifica(
        @NotNull
        LocalDate data
) {
}

package br.com.duxus.desafio.dto.dtoApi;

import java.time.LocalDate;

public record DadosEntradaDatas(
        LocalDate dataInicial,
        LocalDate dataFinal
) {
}

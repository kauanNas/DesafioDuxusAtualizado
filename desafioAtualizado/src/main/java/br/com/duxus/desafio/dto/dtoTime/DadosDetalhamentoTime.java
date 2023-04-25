package br.com.duxus.desafio.dto.dtoTime;

import br.com.duxus.desafio.model.Time;

import java.time.LocalDate;

public record DadosDetalhamentoTime(Long id, LocalDate data) {

    public DadosDetalhamentoTime(Time time){
        this(time.getId(), time.getData());
    }

}

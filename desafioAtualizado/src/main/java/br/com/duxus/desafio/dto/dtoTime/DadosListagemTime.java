package br.com.duxus.desafio.dto.dtoTime;

import br.com.duxus.desafio.model.Time;

import java.time.LocalDate;

public record DadosListagemTime(Long id, LocalDate data) {

    public DadosListagemTime(Time time){
        this(time.getId(), time.getData());
    }

}

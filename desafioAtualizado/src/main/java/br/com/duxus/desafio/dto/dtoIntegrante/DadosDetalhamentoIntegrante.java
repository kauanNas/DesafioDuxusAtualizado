package br.com.duxus.desafio.dto.dtoIntegrante;

import br.com.duxus.desafio.model.Integrante;

public record DadosDetalhamentoIntegrante(Long id, String franquia, String nome, String funcao) {

    public DadosDetalhamentoIntegrante(Integrante integrante){
        this(integrante.getId(), integrante.getFranquia(), integrante.getNome(), integrante.getFuncao());
    }

}

package br.com.duxus.desafio.dto.dtoComposicaoTime;

import br.com.duxus.desafio.model.ComposicaoTime;

public record DadosListagemComposicaoTime(Long id, Long idTime, Long idIntegrante) {

    public DadosListagemComposicaoTime(ComposicaoTime composicaoTime){
        this(composicaoTime.getId(), composicaoTime.getTime().getId(), composicaoTime.getIntegrante().getId());
    }

}

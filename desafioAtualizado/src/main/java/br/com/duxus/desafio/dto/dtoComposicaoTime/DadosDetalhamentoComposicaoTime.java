package br.com.duxus.desafio.dto.dtoComposicaoTime;

import br.com.duxus.desafio.model.ComposicaoTime;

public record DadosDetalhamentoComposicaoTime(Long id, Long idTime, Long idIntegrante) {

    public DadosDetalhamentoComposicaoTime(ComposicaoTime composicaoTime){
        this(composicaoTime.getId(), composicaoTime.getTime().getId(), composicaoTime.getIntegrante().getId());
    }

}

package br.com.duxus.desafio.service;

import br.com.duxus.desafio.dto.dtoComposicaoTime.DadosCadastroComposicaoTime;
import br.com.duxus.desafio.dto.dtoComposicaoTime.DadosDetalhamentoComposicaoTime;
import br.com.duxus.desafio.dto.dtoComposicaoTime.DadosListagemComposicaoTime;
import br.com.duxus.desafio.model.ComposicaoTime;
import br.com.duxus.desafio.repository.ComposicaoTimeRepository;
import br.com.duxus.desafio.repository.IntegranteRepository;
import br.com.duxus.desafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComposicaoTimeService {

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;

    public DadosDetalhamentoComposicaoTime cadastrarComposicao(DadosCadastroComposicaoTime dados){
        var time = timeRepository.getReferenceById(dados.idTime());
        var integrante = integranteRepository.getReferenceById(dados.idIntegrante());
        var composicaoTime = new ComposicaoTime(null, time, integrante);
        composicaoTimeRepository.save(composicaoTime);

        return new DadosDetalhamentoComposicaoTime(composicaoTime);
    }

    public Page<DadosListagemComposicaoTime> listarComposicao(Pageable paginacao) {
        var page = composicaoTimeRepository.findAll(paginacao).map(DadosListagemComposicaoTime::new);
        return page;
    }



    public void excluirComposicao(Long id) {
        composicaoTimeRepository.deleteById(id);
    }
}

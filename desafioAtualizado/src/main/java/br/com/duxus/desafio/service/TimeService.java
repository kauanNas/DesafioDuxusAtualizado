package br.com.duxus.desafio.service;

import br.com.duxus.desafio.dto.dtoTime.DadosAtualizacaoTime;
import br.com.duxus.desafio.dto.dtoTime.DadosCadastroTime;
import br.com.duxus.desafio.dto.dtoTime.DadosDetalhamentoTime;
import br.com.duxus.desafio.dto.dtoTime.DadosListagemTime;
import br.com.duxus.desafio.model.Time;
import br.com.duxus.desafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    public DadosDetalhamentoTime cadastrarTime(DadosCadastroTime dados){
        var time = new Time(dados.data());
        repository.save(time);

        return new DadosDetalhamentoTime(time);
    }

    public Page<DadosListagemTime> listarTime(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTime::new);
        return page;
    }

    public DadosDetalhamentoTime atualizarTime(DadosAtualizacaoTime dados) {
        var time = repository.getReferenceById(dados.id());
        Time timeAtualizado = time;
        if(dados.data() != null) {
            timeAtualizado.setData(dados.data());
        }

        return new DadosDetalhamentoTime(timeAtualizado);
    }

    public void excluirTime(Long id) {
        repository.deleteById(id);
    }

    public DadosDetalhamentoTime detalharTime(Long id){
        var time = repository.getReferenceById(id);
        return new DadosDetalhamentoTime(time);
    }

}

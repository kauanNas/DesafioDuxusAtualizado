package br.com.duxus.desafio.service;

import br.com.duxus.desafio.dto.dtoIntegrante.DadosAtualizacaoIntegrante;
import br.com.duxus.desafio.dto.dtoIntegrante.DadosCadastroIntegrante;
import br.com.duxus.desafio.dto.dtoIntegrante.DadosDetalhamentoIntegrante;
import br.com.duxus.desafio.dto.dtoIntegrante.DadosListagemIntegrante;
import br.com.duxus.desafio.model.Integrante;
import br.com.duxus.desafio.repository.IntegranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class IntegranteService {

    @Autowired
    private IntegranteRepository repository;


    public DadosDetalhamentoIntegrante cadastrarIntegrante(DadosCadastroIntegrante dados){
        var integrante = new Integrante(dados.franquia(), dados.nome(), dados.funcao());
        repository.save(integrante);
        return new DadosDetalhamentoIntegrante(integrante);
    }

    public Page<DadosListagemIntegrante> listarIntegrante(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemIntegrante::new);

        return page;
    }

    public DadosDetalhamentoIntegrante atualizarIntegrante(DadosAtualizacaoIntegrante dados) {
        var integrante = repository.getReferenceById(dados.id());
        Integrante integranteAtualizado = integrante;
        if(dados.franquia() != null){
            integranteAtualizado.setFranquia(dados.franquia());
        }
        if(dados.nome() != null){
            integranteAtualizado.setNome(dados.nome());
        }
        if(dados.funcao() != null){
            integranteAtualizado.setFuncao(dados.funcao());
        }

        return new DadosDetalhamentoIntegrante(integranteAtualizado);
    }

    public void excluirIntegrante(Long id) {
        repository.deleteById(id);
    }

    public DadosDetalhamentoIntegrante detalharIntegrante(Long id){
        var integrante = repository.getReferenceById(id);
        return new DadosDetalhamentoIntegrante(integrante);
    }

}



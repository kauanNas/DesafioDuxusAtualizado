package br.com.duxus.desafio.service;

import br.com.duxus.desafio.dto.dtoApi.DadosEntradaDatas;
import br.com.duxus.desafio.dto.dtoApi.DadosDataEspecifica;
import br.com.duxus.desafio.model.ComposicaoTime;
import br.com.duxus.desafio.model.Integrante;
import br.com.duxus.desafio.model.Time;
import br.com.duxus.desafio.repository.ComposicaoTimeRepository;
import br.com.duxus.desafio.repository.IntegranteRepository;
import br.com.duxus.desafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Service
public class ApiService {

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;


    public List<Integrante> buscarIntegrantes() {
        return integranteRepository.findAll();
    }

    public List<Time> buscarTimes() {
        return timeRepository.findAll();
    }

    public List<ComposicaoTime> buscarComposicao() {
        return composicaoTimeRepository.findAll();
    }


    public List<String> timeDaData(DadosDataEspecifica dados) {
        List<String> nomesIntegrantes = new ArrayList<>();

        List<ComposicaoTime> composicoes = buscarComposicao();

        for (ComposicaoTime composicao : composicoes) {
            if (composicao.getTime().getData().isEqual(dados.data())) {
                nomesIntegrantes.add(composicao.getIntegrante().getNome());
            }
        }

        return nomesIntegrantes;
    }

    public String integranteMaisUsado(DadosEntradaDatas dados) {
        Map<String, Integer> integranteQuantidade = new HashMap<>();

        List<ComposicaoTime> composicoes = buscarComposicao();
        for (ComposicaoTime composicao : composicoes) {
            LocalDate dataTime = composicao.getTime().getData();
            if ((dados.dataInicial() == null || !dataTime.isBefore(dados.dataInicial()))
                    && (dados.dataFinal() == null || !dataTime.isAfter(dados.dataFinal()))) {
                String nomeIntegrante = composicao.getIntegrante().getNome();
                Integer quantidade = integranteQuantidade.getOrDefault(nomeIntegrante, 0);
                integranteQuantidade.put(nomeIntegrante, quantidade + 1);
            }
        }

        String integranteMaisUsado = null;
        int maiorQuantidade = 0;
        for (Map.Entry<String, Integer> resultado : integranteQuantidade.entrySet()) {
            if (resultado.getValue() > maiorQuantidade) {
                integranteMaisUsado = resultado.getKey();
                maiorQuantidade = resultado.getValue();
            }
        }

        return integranteMaisUsado;
    }

    public List<String> timeMaisComum(DadosEntradaDatas dados) {

        List<ComposicaoTime> composicoes = buscarComposicao();
        List<ComposicaoTime> composicoesPeriodo = new ArrayList<>();
        for (ComposicaoTime composicao : composicoes) {
            LocalDate dataTime = composicao.getTime().getData();
            if ((dados.dataInicial() == null || !dataTime.isBefore(dados.dataInicial()))
                    && (dados.dataFinal() == null || !dataTime.isAfter(dados.dataFinal()))) {
                composicoesPeriodo.add(composicao);
            }
        }

        Map<Time, Integer> timeQuantidade = new HashMap<>();
        for (ComposicaoTime composicao : composicoesPeriodo) {
            Time time = composicao.getTime();
            Integer quantidade = timeQuantidade.getOrDefault(time, 0);
            timeQuantidade.put(time, quantidade + 1);
        }
        Time timeMaisUtilizado = null;
        int maiorQuantidade = 0;
        for (Map.Entry<Time, Integer> resultado : timeQuantidade.entrySet()) {
            if (resultado.getValue() > maiorQuantidade) {
                timeMaisUtilizado = resultado.getKey();
                maiorQuantidade = resultado.getValue();
            }
        }

        List<String> integrantesTimeMaisComum = new ArrayList<>();
        for (ComposicaoTime composicao : composicoesPeriodo) {
            if (composicao.getTime().equals(timeMaisUtilizado)) {
                integrantesTimeMaisComum.add(composicao.getIntegrante().getNome());
            }
        }

        return integrantesTimeMaisComum;
    }

    public String funcaoMaisComum(DadosEntradaDatas dados) {
        Map<String, Integer> funcaoQuantidade = new HashMap<>();

        List<ComposicaoTime> composicoes = buscarComposicao();
        for (ComposicaoTime composicao : composicoes) {
            LocalDate dataTime = composicao.getTime().getData();
            if ((dados.dataInicial() == null || !dataTime.isBefore(dados.dataInicial()))
                    && (dados.dataFinal() == null || !dataTime.isAfter(dados.dataFinal()))) {
                String funcaoIntegrante = composicao.getIntegrante().getFuncao();
                Integer quantidade = funcaoQuantidade.getOrDefault(funcaoIntegrante, 0);
                funcaoQuantidade.put(funcaoIntegrante, quantidade + 1);
            }
        }

        String funcaoMaisComum = null;
        int maiorQuantidade = 0;
        for (Map.Entry<String, Integer> resultado : funcaoQuantidade.entrySet()) {
            if (resultado.getValue() > maiorQuantidade) {
                funcaoMaisComum = resultado.getKey();
                maiorQuantidade = resultado.getValue();
            }
        }

        return funcaoMaisComum;
    }

    public String franquiaMaisFamosa(DadosEntradaDatas dados) {
        Map<String, Integer> franquiaQuantidade = new HashMap<>();

        List<ComposicaoTime> composicoes = buscarComposicao();
        for (ComposicaoTime composicao : composicoes) {
            LocalDate dataTime = composicao.getTime().getData();
            if ((dados.dataInicial() == null || !dataTime.isBefore(dados.dataInicial()))
                    && (dados.dataFinal() == null || !dataTime.isAfter(dados.dataFinal()))) {
                String franquia = composicao.getIntegrante().getFranquia();
                Integer frequencia = franquiaQuantidade.getOrDefault(franquia, 0);
                franquiaQuantidade.put(franquia, frequencia + 1);
            }
        }

        String franquia = null;
        int maiorFrequencia = 0;
        for (Map.Entry<String, Integer> resultado : franquiaQuantidade.entrySet()) {
            if (resultado.getValue() > maiorFrequencia) {
                franquia = resultado.getKey();
                maiorFrequencia = resultado.getValue();
            }
        }

        return franquia;
    }

    public Map<String, Integer> contagemPorFranquia(DadosEntradaDatas dados) {
        Map<String, Integer> franquiaQuantidade = new HashMap<>();
        List<ComposicaoTime> composicoes = buscarComposicao();

        for (ComposicaoTime composicao : composicoes) {
            LocalDate dataTime = composicao.getTime().getData();
            if ((dados.dataInicial() == null || !dataTime.isBefore(dados.dataInicial()))
                    && (dados.dataFinal() == null || !dataTime.isAfter(dados.dataFinal()))) {
                String nomeFranquia = composicao.getIntegrante().getFranquia();
                Integer quantidade = franquiaQuantidade.getOrDefault(nomeFranquia, 0);
                franquiaQuantidade.put(nomeFranquia, quantidade + 1);
            }
        }

        Map<String, Integer> contagemFranquia = new HashMap<>();
        for (Map.Entry<String, Integer> resultado : franquiaQuantidade.entrySet()) {
            String nomeFranquia = resultado.getKey();
            Integer quantidade = resultado.getValue();
            contagemFranquia.put(nomeFranquia, quantidade);
        }

        return contagemFranquia;
    }

    public Map<String, Integer> contagemPorFuncao(DadosEntradaDatas dados) {
        Map<String, Integer> funcaoQuantidade = new HashMap<>();

        List<ComposicaoTime> composicoes = buscarComposicao();
        for (ComposicaoTime composicao : composicoes) {
            LocalDate dataTime = composicao.getTime().getData();
            if ((dados.dataInicial() == null || !dataTime.isBefore(dados.dataInicial()))
                    && (dados.dataFinal() == null || !dataTime.isAfter(dados.dataFinal()))) {
                String nomeFuncao = composicao.getIntegrante().getFuncao();
                Integer quantidade = funcaoQuantidade.getOrDefault(nomeFuncao, 0);
                funcaoQuantidade.put(nomeFuncao, quantidade + 1);
            }
        }

        Map<String, Integer> resultado = new HashMap<>();
        for (Map.Entry<String, Integer> entrada : funcaoQuantidade.entrySet()) {
            String nomeFuncao = entrada.getKey();
            Integer quantidade = entrada.getValue();
            resultado.put(nomeFuncao, quantidade);
        }

        return resultado;
    }


//    public List<String> timeDaData(DadosDataEspecifica dados) {
//        var lista = integranteRepository.retornarTimeDaData(dados.data());
//        return lista;
//    }
//
//    public String integranteMaisUsado(DadosEntradaDatas dados) {
//        var integrante = integranteRepository.retornarMaisUsado(dados.dataInicial(),dados.dataFinal());
//        return integrante;
//    }
//
//    public List<String> timeMaisComum(DadosEntradaDatas dados) {
//        var lista = integranteRepository.retornarTimeMaisComum(dados.dataInicial(), dados.dataFinal());
//        return lista;
//    }
//
//    public String funcaoMaisComum(DadosEntradaDatas dados) {
//        var funcao = integranteRepository.retornarFuncaoMaisComum(dados.dataInicial(), dados.dataFinal());
//        return funcao;
//    }
//
//    public String franquiaMaisFamosa(DadosEntradaDatas dados) {
//        var franquia = integranteRepository.retornarFranquiaMaisFamosa(dados.dataInicial(), dados.dataFinal());
//        return franquia;
//    }
//
//    public List<Object> contagemPorFranquia(DadosEntradaDatas dados) {
//        var contagem = integranteRepository.retornarContagemPorFranquia(dados.dataInicial(), dados.dataFinal());
//            return contagem;
//        }
//
//    public List<Object> contagemPorFuncao(DadosEntradaDatas dados) {
//        var contagem = integranteRepository.retornarContagemPorFuncao(dados.dataInicial(), dados.dataFinal());
//        return contagem;
//    }
}

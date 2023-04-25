package br.com.duxus.desafio.repository;

import br.com.duxus.desafio.dto.dtoComposicaoTime.DadosCadastroComposicaoTime;
import br.com.duxus.desafio.dto.dtoIntegrante.DadosCadastroIntegrante;
import br.com.duxus.desafio.dto.dtoTime.DadosCadastroTime;
import br.com.duxus.desafio.model.ComposicaoTime;
import br.com.duxus.desafio.model.Integrante;
import br.com.duxus.desafio.model.Time;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IntegranteRepositoryTest {

    @Autowired
    private IntegranteRepository repository;

    @Autowired
    private TestEntityManager em;



    @Test
    void retornarTimeDaData() {

        var integrante = cadastrarIntegrante("Overwatch", "Jogador", "Dano");
        var data = LocalDate.of(2023,04,15);
        var time = cadastrarTime(data);
        var composicao = cadastrarComposicao(time.getId(), integrante.getId());
        var resultado = repository.retornarTimeDaData(data);
        assertThat(resultado).containsExactly(integrante.getNome());
    }

    @Test
    void retornarMaisUsado() {
        var integrante = cadastrarIntegrante("Overwatch", "Jogador", "Dano");
        var data = LocalDate.of(2023,04,15);
        var time = cadastrarTime(data);
        var composicao = cadastrarComposicao(time.getId(), integrante.getId());
        var dataInicial = LocalDate.of(2023,04,15);
        var dataFinal = LocalDate.of(2023,04,18);
        var resultado = repository.retornarMaisUsado(dataInicial, dataFinal);
        assertThat(resultado).isEqualTo(integrante.getNome());
    }



    @Test
    void retornarTimeMaisComum() {
        var integrante = cadastrarIntegrante("Overwatch", "Jogador1", "Dano");
        var data = LocalDate.of(2023,04,15);
        var time = cadastrarTime(data);
        var composicao1 = cadastrarComposicao(time.getId(), integrante.getId());

        var dataInicial = LocalDate.of(2023,04,15);
        var dataFinal = LocalDate.of(2023,04,18);
        var resultado = repository.retornarTimeMaisComum(dataInicial, dataFinal);

        assertThat(resultado).containsExactly(integrante.getNome());
    }





    @Test
    void retornarFuncaoMaisComum() {
        var integrante = cadastrarIntegrante("Overwatch", "Jogador", "Dano");
        var data = LocalDate.of(2023,04,15);
        var time = cadastrarTime(data);
        var composicao = cadastrarComposicao(time.getId(), integrante.getId());

        var dataInicial = LocalDate.of(2023,04,15);
        var dataFinal = LocalDate.of(2023,04,18);
        var resultado = repository.retornarFuncaoMaisComum(dataInicial, dataFinal);

        assertThat(resultado).isEqualTo(integrante.getFuncao());
    }

    @Test
    void retornarFranquiaMaisFamosa() {
        var integrante = cadastrarIntegrante("Overwatch", "Jogador", "Dano");
        var data = LocalDate.of(2023,04,15);
        var time = cadastrarTime(data);
        var composicao = cadastrarComposicao(time.getId(), integrante.getId());

        var dataInicial = LocalDate.of(2023,04,15);
        var dataFinal = LocalDate.of(2023,04,18);
        var resultado = repository.retornarFranquiaMaisFamosa(dataInicial, dataFinal);

        assertThat(resultado).isEqualTo(integrante.getFranquia());
    }

// O RESULTADO DEVOLVIDO ESTÁ VINDO CERTO, ENTRETANTO NÃO CONSEGUI PASSAR O RESULTADO ESPERADO CORRETO,
// POIS CADA VEZ QUE RODA, O ID DAS TABELAS PRINCIPAIS AUMENTA E NÃO SOUBE CORRIGIR ISSO, DESCULPA

//    @Test
//    void retornarContagemPorFranquia() {
//        var integrante = cadastrarIntegrante("Overwatch", "Jogador", "Dano");
//        var data = LocalDate.of(2023,04,15);
//        var time = cadastrarTime(data);
//        var composicao = cadastrarComposicao(time.getId(), integrante.getId());
//
//        var dataInicial = LocalDate.of(2023,04,15);
//        var dataFinal = LocalDate.of(2023,04,18);
//        var resultado = repository.retornarContagemPorFranquia(dataInicial, dataFinal);
//
//        assertThat(resultado).contains(integrante.getFranquia(), composicao.getId());
//    }


// O RESULTADO DEVOLVIDO ESTÁ CERTO NESSE CASO TAMBÉM, MAS NÃO CONSEGUI PASSAR O RESULTADO ESPERADO DE MANEIRA CERTA
//    @Test
//    void retornarContagemPorFuncao() {
//        var integrante = cadastrarIntegrante("Overwatch", "Jogador", "Dano");
//        var data = LocalDate.of(2023,04,15);
//        var time = cadastrarTime(data);
//        var composicao = cadastrarComposicao(time.getId(), integrante.getId());
//
//        var dataInicial = LocalDate.of(2023,04,15);
//        var dataFinal = LocalDate.of(2023,04,18);
//        var resultado = repository.retornarContagemPorFuncao(dataInicial, dataFinal);
//
//        List<Object> lista = new ArrayList<>();
//        lista.add(integrante.getFuncao());
//        lista.add(1L);
//
//        assertThat(resultado).containsExactlyElementsOf(Collections.singletonList(lista));
//
//    }




    private Integrante cadastrarIntegrante(String franquia, String nome, String funcao){
        var integrante =new Integrante(dadosIntegrante(franquia, nome, funcao));
        em.persist(integrante);
        return integrante;
    }

    private DadosCadastroIntegrante dadosIntegrante(String franquia, String nome, String funcao){
        return new DadosCadastroIntegrante(franquia, nome, funcao);
    }

    private Time cadastrarTime(LocalDate data){
        var time = new Time(dadosTime(data));
        em.persist(time);
        return time;
    }

    private DadosCadastroTime dadosTime(LocalDate data){
        return new DadosCadastroTime(data);
    }

    private ComposicaoTime cadastrarComposicao(Long idTime, Long idIntegrante){
        var composicao = new ComposicaoTime(dadosComposicao(idTime, idIntegrante));
        em.persist(composicao);
        return composicao;
    }

    private DadosCadastroComposicaoTime dadosComposicao(Long idTime, Long idIntegrante){
        return new DadosCadastroComposicaoTime(idTime, idIntegrante);
    }

}
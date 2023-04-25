package br.com.duxus.desafio.repository;

import br.com.duxus.desafio.model.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

import java.util.List;

public interface IntegranteRepository extends JpaRepository<Integrante, Long> {

    @Query("SELECT i.nome FROM Integrante i " +
            "INNER JOIN ComposicaoTime ct on ct.integrante.id = i.id " +
            "INNER JOIN Time t on ct.time.id = t.id " +
            "where  t.data = :data")
    List<String> retornarTimeDaData(LocalDate data);


    @Query("""
            SELECT i.nome
            FROM Integrante i
            INNER JOIN ComposicaoTime ct ON i.id = ct.integrante.id
            INNER JOIN Time t ON ct.time.id = t.id
            WHERE (:dataInicial IS NULL OR t.data >= :dataInicial)
            AND (:dataFinal IS NULL OR t.data <= :dataFinal)
            GROUP BY i.id, i.nome
            ORDER BY COUNT(ct.time.id) DESC
            LIMIT 1
            """)
    String retornarMaisUsado(LocalDate dataInicial, LocalDate dataFinal);


    @Query("""
            SELECT i.nome
            FROM Integrante i
            INNER JOIN ComposicaoTime ct ON i.id = ct.integrante.id
            INNER JOIN Time t ON ct.time.id = t.id
            WHERE (:dataInicial IS NULL OR t.data >= :dataInicial)
            AND (:dataFinal IS NULL OR t.data <= :dataFinal)
            AND ct.time.id = (
              SELECT ct2.time.id
              FROM ComposicaoTime ct2
              INNER JOIN Time t2 ON ct2.time.id = t2.id
              WHERE (:dataInicial IS NULL OR t2.data >= :dataInicial)
              AND (:dataFinal IS NULL OR t2.data <= :dataFinal)
              GROUP BY ct2.time.id
              ORDER BY COUNT(DISTINCT ct2.integrante.id) DESC
              LIMIT 1
            )
            """)
    List<String> retornarTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal);


    @Query("""
            SELECT i.funcao
            FROM Integrante i
            INNER JOIN ComposicaoTime ct ON i.id = ct.integrante.id
            INNER JOIN Time t ON ct.time.id = t.id
            WHERE (:dataInicial IS NULL OR t.data >= :dataInicial)
            AND (:dataFinal IS NULL OR t.data <= :dataFinal)
            GROUP BY i.funcao
            ORDER BY COUNT(DISTINCT ct.time.id) DESC
            LIMIT 1
            """)
    String retornarFuncaoMaisComum(LocalDate dataInicial, LocalDate dataFinal);

    @Query("""
            SELECT i.franquia
            FROM Integrante i
            INNER JOIN ComposicaoTime ct ON i.id = ct.integrante.id
            INNER JOIN Time t ON ct.time.id = t.id
            WHERE (:dataInicial IS NULL OR t.data >= :dataInicial)
            AND (:dataFinal IS NULL OR t.data <= :dataFinal)
            GROUP BY i.franquia
            ORDER BY COUNT(DISTINCT ct.time.id) DESC
            LIMIT 1
            """)
    String retornarFranquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal);


    @Query("""
            SELECT i.franquia, COUNT(ct.id)
            FROM Integrante i
            INNER JOIN ComposicaoTime ct ON i.id = ct.integrante.id
            INNER JOIN Time t ON ct.time.id = t.id
            WHERE (:dataInicial IS NULL OR t.data >= :dataInicial)
            AND (:dataFinal IS NULL OR t.data <= :dataFinal)
            GROUP BY i.franquia
            """)
    List<Object> retornarContagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal);


    @Query("""
            SELECT i.funcao, COUNT(i.funcao)
            FROM Integrante i
            INNER JOIN ComposicaoTime ct ON i.id = ct.integrante.id
            INNER JOIN Time t ON ct.time.id = t.id
            WHERE (:dataInicial IS NULL OR t.data >= :dataInicial)
            AND (:dataFinal IS NULL OR t.data <= :dataFinal)
            GROUP BY i.funcao
            """)
    List<Object> retornarContagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal);
}

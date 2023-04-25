package br.com.duxus.desafio.model;

import br.com.duxus.desafio.dto.dtoIntegrante.DadosCadastroIntegrante;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "integrante")
@Entity(name = "Integrante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Integrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String franquia;

    private String nome;

    private String funcao;

    public Integrante(String franquia, String nome, String funcao){
        this.franquia = franquia;
        this.nome = nome;
        this.funcao = funcao;
    }


    public Integrante(DadosCadastroIntegrante dadosIntegrante) {
        this.franquia = dadosIntegrante.franquia();
        this.nome = dadosIntegrante.nome();
        this.funcao = dadosIntegrante.funcao();
    }
}

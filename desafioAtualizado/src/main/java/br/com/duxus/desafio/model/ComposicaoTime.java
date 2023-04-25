package br.com.duxus.desafio.model;

import br.com.duxus.desafio.dto.dtoComposicaoTime.DadosCadastroComposicaoTime;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "composicaotime")
@Entity(name = "ComposicaoTime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ComposicaoTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Time")
    private Time time = new Time();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Integrante")
    private Integrante integrante = new Integrante();



    public ComposicaoTime(DadosCadastroComposicaoTime dadosComposicao) {
        this.time.setId(dadosComposicao.idTime());
        this.integrante.setId(dadosComposicao.idIntegrante());
    }
}

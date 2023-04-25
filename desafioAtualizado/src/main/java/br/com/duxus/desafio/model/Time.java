package br.com.duxus.desafio.model;

import br.com.duxus.desafio.dto.dtoTime.DadosCadastroTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "time")
@Entity(name = "Time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    public Time(LocalDate data){
        this.data = data;
    }


    public Time(DadosCadastroTime dadosTime) {
        this.data = dadosTime.data();
    }
}

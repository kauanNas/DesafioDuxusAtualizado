package br.com.duxus.desafio.repository;

import br.com.duxus.desafio.model.ComposicaoTime;
import br.com.duxus.desafio.model.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTime,Long> {



}

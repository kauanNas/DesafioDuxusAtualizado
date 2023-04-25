package br.com.duxus.desafio.repository;

import br.com.duxus.desafio.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long> {

}

package br.com.duxus.desafio.controller;

import br.com.duxus.desafio.dto.dtoTime.DadosAtualizacaoTime;
import br.com.duxus.desafio.dto.dtoTime.DadosCadastroTime;
import br.com.duxus.desafio.service.TimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/times")
@SecurityRequirement(name = "bearer-key")
public class TimeController {

    @Autowired
    private TimeService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTime dados, UriComponentsBuilder uriBuilder){
        var dto = service.cadastrarTime(dados);
        var uri = uriBuilder.path("/times/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault(size = 10) Pageable paginacao){
        var dto = service.listarTime(paginacao);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTime dados){
        var dto = service.atualizarTime(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.excluirTime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var dto = service.detalharTime(id);
        return ResponseEntity.ok(dto);
    }

}

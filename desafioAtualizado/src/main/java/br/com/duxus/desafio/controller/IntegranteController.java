package br.com.duxus.desafio.controller;

import br.com.duxus.desafio.dto.dtoIntegrante.DadosAtualizacaoIntegrante;
import br.com.duxus.desafio.dto.dtoIntegrante.DadosCadastroIntegrante;
import br.com.duxus.desafio.service.IntegranteService;
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
@RequestMapping("/integrantes")
@SecurityRequirement(name = "bearer-key")
public class IntegranteController {

    @Autowired
    private IntegranteService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroIntegrante dados, UriComponentsBuilder uriBuilder){
        var dto = service.cadastrarIntegrante(dados);
        var uri = uriBuilder.path("/integrantes/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault(size = 10) Pageable paginacao){
        var dto = service.listarIntegrante(paginacao);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoIntegrante dados){
        var dto = service.atualizarIntegrante(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.excluirIntegrante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var dto = service.detalharIntegrante(id);
        return ResponseEntity.ok(dto);
    }

}

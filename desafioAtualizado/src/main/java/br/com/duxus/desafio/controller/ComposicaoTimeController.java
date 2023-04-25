package br.com.duxus.desafio.controller;

import br.com.duxus.desafio.dto.dtoComposicaoTime.DadosCadastroComposicaoTime;
import br.com.duxus.desafio.service.ComposicaoTimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/composicao")
@SecurityRequirement(name = "bearer-key")
public class ComposicaoTimeController {

    @Autowired
    private ComposicaoTimeService service;



    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroComposicaoTime dados){
        var dto = service.cadastrarComposicao(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault(size = 10) Pageable paginacao){
        var dto = service.listarComposicao(paginacao);
        return ResponseEntity.ok(dto);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.excluirComposicao(id);
        return ResponseEntity.noContent().build();
    }



}

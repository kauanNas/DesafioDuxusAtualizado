package br.com.duxus.desafio.controller;

import br.com.duxus.desafio.dto.dtoApi.DadosEntradaDatas;
import br.com.duxus.desafio.dto.dtoApi.DadosDataEspecifica;
import br.com.duxus.desafio.service.ApiService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearer-key")
public class ApiController {

    @Autowired
    private ApiService service;



    @PostMapping("/timeDaData")
    @Transactional
    public ResponseEntity retornarTimeDaData(@RequestBody @Valid DadosDataEspecifica dados){
        var dto = service.timeDaData(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/integranteMaisUsado")
    @Transactional
    public ResponseEntity retornarIntegranteMaisUsado(@RequestBody DadosEntradaDatas dados){
        var dto = service.integranteMaisUsado(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/timeMaisComum")
    @Transactional
    public ResponseEntity retornarTimeMaisComum(@RequestBody DadosEntradaDatas dados){
        var dto = service.timeMaisComum(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/funcaoMaisComum")
    @Transactional
    public ResponseEntity retornarFuncaoMaisComum(@RequestBody DadosEntradaDatas dados){
        var dto = service.funcaoMaisComum(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/franquiaMaisFamosa")
    @Transactional
    public ResponseEntity retornarFranquiaMaisFamosa(@RequestBody DadosEntradaDatas dados){
        var dto = service.franquiaMaisFamosa(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/contagemPorFranquia")
    @Transactional
    public ResponseEntity retornarContagemPorFranquia(@RequestBody DadosEntradaDatas dados){
        var dto = service.contagemPorFranquia(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/contagemPorFuncao")
    @Transactional
    public ResponseEntity retornarContagemPorFuncao(@RequestBody DadosEntradaDatas dados){
        var dto = service.contagemPorFuncao(dados);
        return ResponseEntity.ok(dto);
    }

}

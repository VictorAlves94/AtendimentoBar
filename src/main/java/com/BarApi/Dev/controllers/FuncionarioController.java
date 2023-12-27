package com.BarApi.Dev.controllers;

import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.dto.FuncionarioCadastrarDto;
import com.BarApi.Dev.domain.dto.FuncionarioListarDto;
import com.BarApi.Dev.services.FuncionarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/funcionarios")
public class FuncionarioController {
@Autowired
    FuncionarioServices service;

    @PostMapping
    @Transactional

    public ResponseEntity cadastrarFuncionario (@RequestBody FuncionarioCadastrarDto dados, UriComponentsBuilder uriBuilder){
       service.cadastrarFuncionario(dados);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioListarDto>> findAll(){
        List<Funcionario> list = service.findAll();
        List<FuncionarioListarDto> listDTO = list.stream().map(obj -> new FuncionarioListarDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

    }
}

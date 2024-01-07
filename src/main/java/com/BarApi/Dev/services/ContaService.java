package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Conta;
import com.BarApi.Dev.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;

    public Conta buscarPorId(Long aLong) {
        return contaRepository.findById(aLong).orElseThrow(() -> new RuntimeException("Conta Não Encontrada!"));
    }
}

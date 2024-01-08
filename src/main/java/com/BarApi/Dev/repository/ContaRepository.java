package com.BarApi.Dev.repository;

import com.BarApi.Dev.domain.Cliente;
import com.BarApi.Dev.domain.Conta;
import com.BarApi.Dev.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta,Long> {
    Optional<Conta> findById(Long id);

    Optional<Cliente> findByNome(String nome);
}

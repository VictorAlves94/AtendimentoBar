package com.BarApi.Dev.repository;

import com.BarApi.Dev.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findById(Long id);

    Optional<Funcionario> findByNome(String s);
}

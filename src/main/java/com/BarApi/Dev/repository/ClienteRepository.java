package com.BarApi.Dev.repository;

import com.BarApi.Dev.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByCodigo(Integer id);
}

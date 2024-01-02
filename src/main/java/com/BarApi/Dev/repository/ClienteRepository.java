package com.BarApi.Dev.repository;

import com.BarApi.Dev.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}

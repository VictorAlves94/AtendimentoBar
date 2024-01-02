package com.BarApi.Dev.repository;

import com.BarApi.Dev.domain.Cliente;
import com.BarApi.Dev.domain.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedidos,Long> {
}

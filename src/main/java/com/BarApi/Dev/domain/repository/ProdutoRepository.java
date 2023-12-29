package com.BarApi.Dev.domain.repository;

import com.BarApi.Dev.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}

package com.BarApi.Dev.dto.cliente;

import com.BarApi.Dev.domain.Conta;
import com.BarApi.Dev.domain.Pedidos;
import com.BarApi.Dev.enuns.Funcao;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ClienteListarDto(Long id,

                               String nome,
                               Integer codigo,
                               LocalDateTime  horaCriacao,
                               LocalDateTime horaFinalizando,
                                Conta conta
){}


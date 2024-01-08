package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.*;
import com.BarApi.Dev.repository.ClienteRepository;
import com.BarApi.Dev.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    PedidosService pedidosService;
    @Autowired
    ClienteRepository clienteRepository;


    public Conta criarConta(Cliente cliente) {
        Conta conta = new Conta();
        conta.setCliente(cliente);
        return contaRepository.save(conta);
    }




    public Conta buscarPorId(Long aLong) {
        return contaRepository.findById(aLong).orElseThrow(() -> new RuntimeException("Conta Não Encontrada!"));
    }
    public Pedidos adicionarPedido(Conta conta, Funcionario garcon, List<Produto> produtos, String mesa) {
        Pedidos pedido = new Pedidos();
        pedido.setConta(conta);
        pedido.setGarcon(garcon);
        pedido.setProdutos(produtos);
        pedido.setMesa(mesa);

        // Atualize o valor total da conta
        conta.adicionarPedido(pedido);

        // Salve o pedido e a conta
        pedidosService.salvarPedido(pedido);
        return pedido;
    }


    public void atualizarValorTotal(Long id, BigDecimal valorTotalPedido) {
    }

    public Optional<Cliente> obterOuCriarContaPorCodigoCliente(Integer codigoCliente) {
      var codigoDoCliente = clienteRepository.findByCodigo(codigoCliente);
      return codigoDoCliente;
    }
}

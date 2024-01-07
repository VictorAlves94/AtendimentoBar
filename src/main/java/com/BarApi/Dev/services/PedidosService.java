package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Conta;
import com.BarApi.Dev.domain.Pedidos;
import com.BarApi.Dev.dto.pedidos.PedidosCriarDto;
import com.BarApi.Dev.dto.pedidos.PedidosListarDto;
import com.BarApi.Dev.repository.PedidosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository repository;
    @Autowired
    private FuncionarioServices funcionarioServices;
    @Autowired
    private ContaService contaService;

    public PedidosListarDto buscarPedido(Long id) {
        Pedidos pedido = buscarPorId(id);
        return converterEntidadeParaListarDto(pedido);
    }

    public Page<PedidosListarDto> listarTodos(Pageable paginacao) {
        Page<Pedidos> pagina = repository.findAll(paginacao);

        List<PedidosListarDto> dadosList = pagina.getContent()
                .stream()
                .map(this::converterEntidadeParaListarDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    @Transactional
    public Pedidos cadastrarPedido(PedidosCriarDto dados) {
        Pedidos pedidoCadastro = converterDtoParaEntidade(dados);
        var funcionario = funcionarioServices.buscarPorNome(dados.nomeGarcon());
        var cliente = clienteServices.buscarPorCodigo(dados.codigoCliente());

        pedidoCadastro.setGarcon(funcionario);
        pedidoCadastro.setCliente(cliente);

        return repository.save(pedidoCadastro);
    }

    @Transactional
    public Pedidos atualizarPedido(PedidosCriarDto pedidosCriarDto, long id) {
        Pedidos pedido = buscarPorId(id);
        Pedidos pedidoEntrada = converterDtoParaEntidade(pedidosCriarDto);
        pedidoEntrada.setId(id);


       BeanUtils.copyProperties(pedidoEntrada, pedido);

        return repository.save(pedido);
    }

    public void deletarPedido(Long id) {
        var pedido = buscarPedido(id);

        if (pedido != null) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Este Pedido Não Existe.");
        }
    }

    private PedidosListarDto converterEntidadeParaListarDto(Pedidos pedido) {
        return new PedidosListarDto(
                pedido.getId(),
                pedido.getCliente(),
                pedido.getGarcon(),
                pedido.getProdutos(),
                pedido.getMesa()
                // Adicione outros campos conforme necessário
        );
    }

    private Pedidos converterDtoParaEntidade(PedidosCriarDto pedidoCriarDto) {
        Pedidos pedido = new Pedidos();

        // ... outros campos ...

        var conta = contaService.buscarPorId(pedidoCriarDto.contaId());
        pedido.setConta(conta);

        // Configure o pedido com os dados do DTO
        pedido.setGarcon(funcionarioServices.buscarPorNome(pedidoCriarDto.nomeGarcon()));
        // Outros campos conforme necessário

        return pedido;
    }
    }

    private Pedidos buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não Encontrado"));
    }
}

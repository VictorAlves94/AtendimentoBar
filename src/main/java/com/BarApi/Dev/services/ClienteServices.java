package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Cliente;
import com.BarApi.Dev.domain.Pedidos;
import com.BarApi.Dev.dto.cliente.ClienteCadastrarDto;
import com.BarApi.Dev.dto.cliente.ClienteListarDto;
import com.BarApi.Dev.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServices {
    @Autowired
    ClienteRepository repository;

    public ClienteListarDto buscarUsuario(Long id) {
        Cliente cliente = buscarPorId(id);
        return converterEntidadeParaListarDto(cliente);
    }

    public Page<ClienteListarDto> listarTodos(Pageable paginacao) {
        Page<Cliente> pagina = repository.findAll(paginacao);

        List<ClienteListarDto> dadosList = pagina.getContent()
                .stream()
                .map(this::converterEntidadeParaListarDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    @Transactional
    public Cliente cadastrarCliente(ClienteCadastrarDto dados) {
        Cliente clienteCadastro = converterDtoParaEntidade(dados);
        return repository.save(clienteCadastro);
    }

    @Transactional
    public Cliente atualizarUsuario(ClienteCadastrarDto clienteCadastrarDto, long id) {
        Cliente cliente = buscarPorId(id);
        Cliente clienteEntrada = converterDtoParaEntidade(clienteCadastrarDto);
        clienteEntrada.setId(id);

        // Aqui você poderia usar um método específico para cópia ou um mapeador como ModelMapper ou MapStruct
        BeanUtils.copyProperties(clienteEntrada, cliente);

        return repository.save(cliente);
    }

    @Transactional
    public void excluirCliente(Long id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            cliente.setHoraFinalizando(LocalDateTime.now());
            repository.save(cliente);
        } else {
            throw new RuntimeException("Este Cliente Não Existe.");
        }
    }


    private ClienteListarDto converterEntidadeParaListarDto(Cliente cliente) {
        return new ClienteListarDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCodigo(),
                cliente.getHoraCriacao(),
                cliente.getHoraFinalizando(),
                cliente.getPedidos(),
                cliente.getContaTotal()
        );
    }

    private Cliente converterDtoParaEntidade(ClienteCadastrarDto clienteCadastrarDto) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteCadastrarDto.nome());
        cliente.setCodigo(clienteCadastrarDto.codigo());
        cliente.setPedidos((Pedidos) clienteCadastrarDto.pedidos());
        cliente.setContaTotal(clienteCadastrarDto.contaTotal());

        return cliente;
    }

    private Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não Encontrado"));
    }
    public Cliente buscarPorCodigo(Integer id) {
        return repository.findByCodigo(id).orElseThrow(() -> new RuntimeException("Cliente não Encontrado"));
    }
}

package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.dto.funcionario.FuncionarioCadastrarDto;
import com.BarApi.Dev.dto.funcionario.FuncionarioListarDto;
import com.BarApi.Dev.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServices {
    @Autowired
    FuncionarioRepository repository;

    public FuncionarioListarDto buscarUsuario(Long id) {
        Funcionario usuario = buscarPorId(id);
        return converterEntidadeParaListarDto(usuario);
    }

    public Page<FuncionarioListarDto> listarTodos(Pageable paginacao) {
        Page<Funcionario> pagina = repository.findAll(paginacao);

        List<FuncionarioListarDto> dadosList = pagina.getContent()
                .stream()
                .map(this::converterEntidadeParaListarDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    @Transactional
    public Funcionario cadastrarFuncionario(FuncionarioCadastrarDto dados) {
        Funcionario funcionarioCadastro = converterDtoParaEntidade(dados);
        return repository.save(funcionarioCadastro);
    }

    @Transactional
    public Funcionario atualizarUsuario(FuncionarioCadastrarDto funcionarioDto, long id) {
        Funcionario usuario = buscarPorId(id);
        Funcionario usuarioEntrada = converterDtoParaEntidade(funcionarioDto);
        usuarioEntrada.setId(id);

        // Aqui você poderia usar um método específico para cópia ou um mapeador como ModelMapper ou MapStruct
        // BeanUtils.copyProperties(usuarioEntrada, usuario);

        return repository.save(usuario);
    }

    public void demitirFuncionario(Long id) {
        var funcionario = buscarPorId(id);

        if (!funcionario.getAtivo()) {
            throw new RuntimeException("Funcionário já inativo.");
        }

        // Realiza a exclusão lógica
        funcionario.setAtivo(false);
        funcionario.setDataDemissao(LocalDate.now());

        // Salva as alterações no banco de dados
        repository.save(funcionario);
    }

    private FuncionarioListarDto converterEntidadeParaListarDto(Funcionario funcionario) {
        return new FuncionarioListarDto(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCpf(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                funcionario.getSenha(),
                funcionario.getFuncao(),
                funcionario.getDataAdmissao(),
                funcionario.getDataDemissao(),
                funcionario.getAtivo()
        );
    }

    private Funcionario converterDtoParaEntidade(FuncionarioCadastrarDto funcionarioDto) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioDto.nome());
        funcionario.setSobrenome(funcionarioDto.sobrenome());
        funcionario.setCpf(funcionarioDto.cpf());
        funcionario.setTelefone(funcionarioDto.telefone());
        funcionario.setEmail(funcionarioDto.email());
        funcionario.setSenha(funcionarioDto.senha());
        funcionario.setFuncao(funcionarioDto.funcao());

        return funcionario;
    }

    private Funcionario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não Encontrado"));
    }
}

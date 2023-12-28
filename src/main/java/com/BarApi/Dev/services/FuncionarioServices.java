package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.dto.FuncionarioCadastrarDto;
import com.BarApi.Dev.domain.dto.FuncionarioListarDto;
import com.BarApi.Dev.domain.repository.FuncionarioRepository;
import com.fasterxml.jackson.databind.BeanProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServices {
    @Autowired
    FuncionarioRepository repository;

    public FuncionarioListarDto buscarUsuario(Long id) {
        Optional<Funcionario> usuarioOptional = repository.findById(id);

        if (usuarioOptional.isPresent()) {
            Funcionario usuario = usuarioOptional.get();
            FuncionarioListarDto dados = new FuncionarioListarDto(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getSobrenome(),
                    usuario.getCpf(),
                    usuario.getTelefone(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getFuncao(),
                    usuario.getDataCriacao()
            );
            return dados;
        } else {
            return null;
        }
    }

    public Page<FuncionarioListarDto> listarTodos(Pageable paginacao) {
        Page<Funcionario> pagina = repository.findAll(paginacao);
        List<Funcionario> usersList = pagina.getContent();

        List<FuncionarioListarDto> dadosList = new ArrayList<>();

        usersList.forEach(usuario -> {
            FuncionarioListarDto dados = new FuncionarioListarDto(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getSobrenome(),
                    usuario.getCpf(),
                    usuario.getTelefone(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getFuncao(),
                    usuario.getDataCriacao());

            dadosList.add(dados);
        });

        return new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    public Funcionario cadastrarFuncionario(FuncionarioCadastrarDto dados) {
        Funcionario funcionarioCadastro = converterDtoEntidade(dados);
        var abc = repository.save(funcionarioCadastro);
        return abc;
    }

    public Object atualizarUsuario(FuncionarioCadastrarDto funcionarioDto, long id) {
        Optional<Funcionario> usuarioOptional = repository.findById(id);

        var usuarioEntrada = converterDtoEntidade(funcionarioDto);
            usuarioEntrada.setId(id);
        if (usuarioOptional.isPresent()) {
            var usuario = usuarioOptional.get();

            BeanUtils.copyProperties(usuarioEntrada, usuario);
            var usuarioAtualizado = repository.save(usuario);

            return usuarioAtualizado;
        }


        return null;
    }


    private Funcionario converterDtoEntidade(FuncionarioCadastrarDto funcionarioDto) {
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

}


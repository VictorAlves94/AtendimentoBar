package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.dto.FuncionarioCadastrarDto;
import com.BarApi.Dev.domain.dto.FuncionarioListarDto;
import com.BarApi.Dev.domain.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioServices {
    @Autowired
    FuncionarioRepository repository;

    public FuncionarioListarDto buscarUsuario(Long id) {
        Funcionario usuario = buscarPorId(id);

            FuncionarioListarDto dados = new FuncionarioListarDto(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getSobrenome(),
                    usuario.getCpf(),
                    usuario.getTelefone(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getFuncao(),
                    usuario.getDataAdmissao(),
                    usuario.getDataDemissao(),
                    usuario.getAtivo()
            );
            return dados;

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
                    usuario.getDataAdmissao(),
                    usuario.getDataDemissao(),
                    usuario.getAtivo());

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
        Funcionario usuario = buscarPorId(id);

        var usuarioEntrada = converterDtoEntidade(funcionarioDto);
            usuarioEntrada.setId(id);


            BeanUtils.copyProperties(usuarioEntrada, usuario);
            var usuarioAtualizado = repository.save(usuario);

            return usuarioAtualizado;
        }



    public void demitirFuncionario(Long id) {
        var funcionario = buscarPorId(id);

        if (funcionario.getAtivo().booleanValue() == false) {
            // Pode lançar uma exceção, retornar um erro ou fazer algo apropriado ao seu aplicativo
            throw new RuntimeException("Funcionário já inativo.");
        } else {

            // Realiza a exclusão lógica
            funcionario.setAtivo(false);
            funcionario.setDataDemissao(LocalDate.now());

            // Salva as alterações no banco de dados
            repository.save(funcionario);
        }
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


    private Funcionario buscarPorId(Long id){
        Funcionario usuarioOptional = repository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não Encontrado"));


        return usuarioOptional;
    }
}


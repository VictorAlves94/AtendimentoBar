package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.dto.FuncionarioCadastrarDto;
import com.BarApi.Dev.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServices {
    @Autowired
    FuncionarioRepository repository;

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public void  cadastrarFuncionario(FuncionarioCadastrarDto dados) {
        Funcionario funcionarioCadastro = converterCadastroEntidade(dados);
       repository.save(funcionarioCadastro);
    }












        private Funcionario converterCadastroEntidade(FuncionarioCadastrarDto cadastro){

        Funcionario funcionario = new Funcionario();

            funcionario.setNome(cadastro.nome());
            funcionario.setSobrenome(cadastro.sobrenome());
            funcionario.setCpf(cadastro.cpf());
            funcionario.setTelefone(cadastro.telefone());
            funcionario.setEmail(cadastro.email());
            funcionario.setSenha(cadastro.senha());
            funcionario.setFuncao(cadastro.funcao());


            return funcionario;
        }
}


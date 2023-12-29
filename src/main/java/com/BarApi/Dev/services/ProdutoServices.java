package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Produto;
import com.BarApi.Dev.dto.produto.ProdutoListarDto;
import com.BarApi.Dev.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServices {
    @Autowired
    ProdutoRepository repository;

    public ProdutoListarDto buscarProduto(Long id) {
        Produto produto = buscarPorId(id);

        ProdutoListarDto dados = new ProdutoListarDto(
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getValor(),
                    produto.getCategoriaEnum(),
                    produto.getQuantEstoque()

            );
            return dados;

    }

    public Page<ProdutoListarDto> listarTodos(Pageable paginacao) {
        Page<Produto> pagina = repository.findAll(paginacao);
        List<Produto> usersList = pagina.getContent();

        List<ProdutoListarDto> dadosList = new ArrayList<>();

        usersList.forEach(produto -> {
            ProdutoListarDto dados = new ProdutoListarDto(
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getValor(),
                    produto.getCategoriaEnum(),
                    produto.getQuantEstoque());

            dadosList.add(dados);
        });

        return new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    public Produto cadastrarProdutos(ProdutoListarDto dados) {
        Produto produtoCadastro = converterDtoEntidade(dados);
        var produtoSalvo = repository.save(produtoCadastro);
        return produtoSalvo;
    }

    public Object atualizarUsuario(ProdutoListarDto produtoListarDto, long id) {
        Produto produto = buscarPorId(id);

        var produtoEntrada = converterDtoEntidade(produtoListarDto);
            produtoEntrada.setId(id);


            BeanUtils.copyProperties(produtoEntrada, produto);
            var produtoAtualizado = repository.save(produto);

            return produtoAtualizado;
        }

    public void deletarProduto(Long id) {
        var produto = buscarProduto(id);

        if (produto != null){
            repository.deleteById(id);
        }
        throw new RuntimeException("Este Produto Não Existe.");
    }

    private Produto converterDtoEntidade(ProdutoListarDto produtoListarDto) {
        Produto produto = new Produto();

        produto.setNome(produtoListarDto.nome());
        produto.setDescricao(produtoListarDto.descricao());
        produto.setValor(produtoListarDto.valor());
        produto.setCategoriaEnum(produtoListarDto.categoriaEnum());
        produto.setQuantEstoque(produtoListarDto.quantEstoque());


        return produto;
    }


    private Produto buscarPorId(Long id){
        Produto produtoOptional = repository.findById(id).orElseThrow(()-> new RuntimeException("Produto não Encontrado"));


        return produtoOptional;
    }
}


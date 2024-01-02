package com.BarApi.Dev.services;

import com.BarApi.Dev.domain.Produto;
import com.BarApi.Dev.dto.produto.ProdutoCriarDto;
import com.BarApi.Dev.dto.produto.ProdutoListarDto;
import com.BarApi.Dev.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Produto cadastrarProdutos(ProdutoCriarDto dados) {
        Produto produtoCadastro = converterDtoEntidade(dados);
        var produtoSalvo = repository.save(produtoCadastro);
        return produtoSalvo;
    }

    @Transactional
    public Object atualizarUsuario(ProdutoCriarDto produtoCriarDto, long id) {
        Produto produto = buscarPorId(id);

        var produtoEntrada = converterDtoEntidade(produtoCriarDto);
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

    private Produto converterDtoEntidade(ProdutoCriarDto produtoCriarDto) {
        Produto produto = new Produto();

        produto.setNome(produtoCriarDto.nome());
        produto.setDescricao(produtoCriarDto.descricao());
        produto.setValor(produtoCriarDto.valor());
        produto.setCategoriaEnum(produtoCriarDto.categoriaEnum());
        produto.setQuantEstoque(produtoCriarDto.quantEstoque());


        return produto;
    }


    private Produto buscarPorId(Long id){
        Produto produtoOptional = repository.findById(id).orElseThrow(()-> new RuntimeException("Produto não Encontrado"));


        return produtoOptional;
    }
}


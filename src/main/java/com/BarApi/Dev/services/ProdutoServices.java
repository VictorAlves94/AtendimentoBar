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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServices {
    @Autowired
    ProdutoRepository repository;

    public ProdutoListarDto buscarProduto(Long id) {
        Produto produto = buscarPorId(id);
        return converterEntidadeParaListarDto(produto);
    }

    public Page<ProdutoListarDto> listarTodos(Pageable paginacao) {
        Page<Produto> pagina = repository.findAll(paginacao);
        List<ProdutoListarDto> dadosList = pagina.getContent()
                .stream()
                .map(this::converterEntidadeParaListarDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    @Transactional
    public Produto cadastrarProdutos(ProdutoCriarDto dados) {
        Produto produtoCadastro = converterDtoParaEntidade(dados);
        return repository.save(produtoCadastro);
    }

    @Transactional
    public Produto atualizarProduto(ProdutoCriarDto produtoCriarDto, long id) {
        Produto produto = buscarPorId(id);
        Produto produtoEntrada = converterDtoParaEntidade(produtoCriarDto);
        produtoEntrada.setId(id);

        // Aqui você poderia usar um método específico para cópia ou um mapeador como ModelMapper ou MapStruct
       BeanUtils.copyProperties(produtoEntrada, produto);

        return repository.save(produto);
    }

    public void deletarProduto(Long id) {
        var produto = buscarProduto(id);

        if (produto != null){
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Este Produto Não Existe.");
        }
    }

    private ProdutoListarDto converterEntidadeParaListarDto(Produto produto) {
        return new ProdutoListarDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValor(),
                produto.getCategoriaEnum(),
                produto.getQuantEstoque()
        );
    }

    private Produto converterDtoParaEntidade(ProdutoCriarDto produtoCriarDto) {
        Produto produto = new Produto();

        produto.setNome(produtoCriarDto.nome());
        produto.setDescricao(produtoCriarDto.descricao());
        produto.setValor(produtoCriarDto.valor());
        produto.setCategoriaEnum(produtoCriarDto.categoriaEnum());
        produto.setQuantEstoque(produtoCriarDto.quantEstoque());

        return produto;
    }

    private Produto buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não Encontrado"));
    }
}

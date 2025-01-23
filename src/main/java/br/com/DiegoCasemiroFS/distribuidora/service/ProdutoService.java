package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProdutoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProdutoNaoEncontradoException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto procuraPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    public List<Produto> listaTodos(){
        return produtoRepository.findAll();
    }

    public Produto cadastraproduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto alteraPreco(Long id, ProdutoRequestDto produtoRequestDto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setPreco(produtoRequestDto.getPreco());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    public Produto alteraEstoque(Long id, ProdutoRequestDto produtoRequestDto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setQuantidade(produtoRequestDto.getQuantidade());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    public void deletaProduto(Long id){
        produtoRepository.deleteById(id);
    }
}

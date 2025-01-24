package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.EstoqueRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.PrecoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProdutoNaoEncontradoException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public Produto alteraPreco(Long id, PrecoRequestDto precoRequestDto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setPreco(precoRequestDto.getPreco());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    public Produto alteraEstoque(Long id, EstoqueRequestDto estoqueRequestDto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setEstoque(estoqueRequestDto.getQuantidade());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    public void deletaProduto(Long id){
        produtoRepository.deleteById(id);
    }
}

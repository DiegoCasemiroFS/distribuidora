package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao.Movimentacao;
import br.com.DiegoCasemiroFS.distribuidora.entity.produto.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao.MovimentacaoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao.MovimentacaoResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.UserType;
import br.com.DiegoCasemiroFS.distribuidora.exception.MovimentacaoNaoEncontradaException;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProdutoNaoEncontradoException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.MovimentacaoRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;

    public Movimentacao procuraPorId(Long id){
        return movimentacaoRepository.findById(id)
                .orElseThrow(MovimentacaoNaoEncontradaException::new);
    }

    public List<Movimentacao> listaTodas(){
        return movimentacaoRepository.findAll();
    }

    public MovimentacaoResponseDto vendaCliente(MovimentacaoRequestDto movimentacaoRequestDto){
        User user = userRepository.findById(movimentacaoRequestDto.getUsuarioId()).orElseThrow(UserNotFoundException::new);
        Produto produto = produtoRepository.findById(movimentacaoRequestDto.getProdutoId()).orElseThrow(ProdutoNaoEncontradoException::new);

        if (produto.getEstoque() >= movimentacaoRequestDto.getQuantidade()){
            produto.setEstoque(produto.getEstoque() - movimentacaoRequestDto.getQuantidade());
            produtoRepository.save(produto);

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setUserId(user);
            movimentacao.setNomeUsuario(user.getName());
            movimentacao.setProdutoId(produto);
            movimentacao.setNomeProduto(produto.getNome());
            movimentacao.setQuantidade(movimentacaoRequestDto.getQuantidade());
            movimentacao.setEstoqueAtual(produto.getEstoque());
            movimentacao.setValorUnitario(produto.getPreco());
            movimentacao.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(movimentacaoRequestDto.getQuantidade())));
            movimentacao.setTipoMovimentacao(2);
            movimentacao.setDataPedido(LocalDateTime.now());

            movimentacaoRepository.save(movimentacao);

            MovimentacaoResponseDto venda = new MovimentacaoResponseDto();
            venda.setNomeUsuario(user.getName());
            venda.setNomeProduto(produto.getNome());
            venda.setQuantidade(movimentacaoRequestDto.getQuantidade());
            venda.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(movimentacaoRequestDto.getQuantidade())));
            venda.setDataVenda(LocalDate.now());

            return venda;
        }
        throw new IllegalArgumentException("Quantidade insuficiente em estoque");
    }

    public MovimentacaoResponseDto compraFornecedor(MovimentacaoRequestDto movimentacaoRequestDto){
        User user = userRepository.findById(movimentacaoRequestDto.getUsuarioId()).orElseThrow(UserNotFoundException::new);
        Produto produto = produtoRepository.findById(movimentacaoRequestDto.getProdutoId()).orElseThrow(ProdutoNaoEncontradoException::new);

        if (user.getUserType() == UserType.FORNECEDOR){
            produto.setEstoque(produto.getEstoque() + movimentacaoRequestDto.getQuantidade());
            produtoRepository.save(produto);

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setUserId(user);
            movimentacao.setNomeUsuario(user.getName());
            movimentacao.setProdutoId(produto);
            movimentacao.setNomeProduto(produto.getNome());
            movimentacao.setQuantidade(movimentacaoRequestDto.getQuantidade());
            movimentacao.setEstoqueAtual(produto.getEstoque());
            movimentacao.setValorUnitario(produto.getPreco());
            movimentacao.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(movimentacaoRequestDto.getQuantidade())));
            movimentacao.setTipoMovimentacao(1);
            movimentacao.setDataPedido(LocalDateTime.now());

            movimentacaoRepository.save(movimentacao);

            MovimentacaoResponseDto compra = new MovimentacaoResponseDto();
            compra.setNomeUsuario(user.getName());
            compra.setNomeProduto(produto.getNome());
            compra.setQuantidade(movimentacaoRequestDto.getQuantidade());
            compra.setDataVenda(LocalDate.now());

            return compra;
        }
        throw new IllegalArgumentException("Usuário não é um fornecedor");
    }

    public void deletaMovimentacao(Long id){
        movimentacaoRepository.deleteById(id);
    }
}

package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Movimentacao;
import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.Usuario;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.MovimentacaoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.MovimentacaoResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.TipoUsuario;
import br.com.DiegoCasemiroFS.distribuidora.exception.MovimentacaoNaoEncontradaException;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProdutoNaoEncontradoException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UsuarioNaoEncontradoException;
import br.com.DiegoCasemiroFS.distribuidora.repository.MovimentacaoRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public Movimentacao procuraPorId(Long id){
        return movimentacaoRepository.findById(id)
                .orElseThrow(MovimentacaoNaoEncontradaException::new);
    }

    public List<Movimentacao> listaTodas(){
        return movimentacaoRepository.findAll();
    }

    public MovimentacaoResponseDto vendaCliente(MovimentacaoRequestDto movimentacaoRequestDto){
        Usuario usuario = usuarioRepository.findById(movimentacaoRequestDto.getUsuarioId()).orElseThrow(UsuarioNaoEncontradoException::new);
        Produto produto = produtoRepository.findById(movimentacaoRequestDto.getProdutoId()).orElseThrow(ProdutoNaoEncontradoException::new);

        if (produto.getEstoque() > movimentacaoRequestDto.getQuantidade()){
            produto.setEstoque(produto.getQuantidade() - movimentacaoRequestDto.getQuantidade());
            produtoRepository.save(produto);

            MovimentacaoResponseDto venda = new MovimentacaoResponseDto();
            venda.setNomeUsuario(usuario.getNome());
            venda.setNomeProduto(produto.getNome());
            venda.setQuantidade(movimentacaoRequestDto.getQuantidade());
            venda.setValorTotal(produto.getValorTotal());
            venda.setDataVenda(LocalDate.now());

            return venda;
        }
        throw new IllegalArgumentException("Quantidade insuficiente em estoque");
    }

    public MovimentacaoResponseDto compraFornecedor(MovimentacaoRequestDto movimentacaoRequestDto){
        Usuario usuario = usuarioRepository.findById(movimentacaoRequestDto.getUsuarioId()).orElseThrow(UsuarioNaoEncontradoException::new);
        Produto produto = produtoRepository.findById(movimentacaoRequestDto.getProdutoId()).orElseThrow(ProdutoNaoEncontradoException::new);

        if (usuario.getTipoUsuario() == TipoUsuario.FORNECEDOR){
            produto.setEstoque(produto.getEstoque() + movimentacaoRequestDto.getQuantidade());
            produtoRepository.save(produto);

            MovimentacaoResponseDto compra = new MovimentacaoResponseDto();
            compra.setNomeUsuario(usuario.getNome());
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

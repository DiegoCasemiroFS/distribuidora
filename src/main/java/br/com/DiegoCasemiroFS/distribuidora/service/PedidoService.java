package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Pedido;
import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.Usuario;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.PedidoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.OrderNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.PedidoRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public Pedido findById(Long id){
        return pedidoRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido createOrder(PedidoRequestDto pedidoRequestDto){
        Usuario usuario = usuarioRepository.findById(pedidoRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Produto produto = produtoRepository.findById(pedidoRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuario);
        pedido.setProdutoId(produto);
        pedido.setQuantity(pedidoRequestDto.getQuantity());

        return pedido;
    }

    public Pedido updateOrder(Long id, PedidoRequestDto pedidoRequestDto){
        Usuario usuario = usuarioRepository.findById(pedidoRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Produto produto = produtoRepository.findById(pedidoRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setUsuarioId(usuario);
                    pedido.setProdutoId(produto);
                    pedido.setQuantity(pedidoRequestDto.getQuantity());
                    return pedido;
                }).orElseThrow(OrderNotFoundException::new);
    }

    public void deleteOrder(Long id){
        pedidoRepository.deleteById(id);
    }
}

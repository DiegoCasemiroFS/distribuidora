package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.OrderRequestDto;
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

    public Order findById(Long id){
        return pedidoRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> findAll(){
        return pedidoRepository.findAll();
    }

    public Order createOrder(OrderRequestDto orderRequestDto){
        User user = usuarioRepository.findById(orderRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = produtoRepository.findById(orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        Order order = new Order();
        order.setUserId(user);
        order.setProductId(product);
        order.setQuantity(orderRequestDto.getQuantity());

        return order;
    }

    public Order updateOrder(Long id, OrderRequestDto orderRequestDto){
        User user = usuarioRepository.findById(orderRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = produtoRepository.findById(orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        return pedidoRepository.findById(id)
                .map(order -> {
                    order.setUserId(user);
                    order.setProductId(product);
                    order.setQuantity(orderRequestDto.getQuantity());
                    return order;
                }).orElseThrow(OrderNotFoundException::new);
    }

    public void deleteOrder(Long id){
        pedidoRepository.deleteById(id);
    }
}

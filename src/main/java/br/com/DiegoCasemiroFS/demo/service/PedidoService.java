package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Pedido;
import br.com.DiegoCasemiroFS.demo.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  @Autowired
  PedidoRepository pedidoRepository;

  public Pedido findById(Long id){
    return pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Pedido> listPedido(){
    return pedidoRepository.findAll();
  }

  public Pedido createPedido(Pedido pedido){
    pedidoRepository.save(pedido);
    return pedido;
  }

  public Pedido updatePedido(Long id, Pedido pedido){
    return pedidoRepository.findById(id)
        .map(f -> {
          pedido.setId(f.getId());
          return pedido;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deletePedido(Long id){
    pedidoRepository.findById(id)
        .map(f -> {
          pedidoRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}

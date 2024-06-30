package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Cliente;
import br.com.DiegoCasemiroFS.demo.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  ClienteRepository clienteRepository;

  public Cliente findById(Long id){
    return clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Cliente> listCliente(){
    return clienteRepository.findAll();
  }

  public Cliente createCliente(Cliente cliente){
    clienteRepository.save(cliente);
    return cliente;
  }

  public Cliente updateCliente(Long id, Cliente cliente){
    return clienteRepository.findById(id)
        .map(f -> {
          cliente.setId(f.getId());
          clienteRepository.save(cliente);
          return cliente;
        }).orElseThrow(()->new RuntimeException("Id não encontrado"));
  }

  public void deleteCliente(Long id){
    clienteRepository.findById(id)
        .map(f->{
          clienteRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(()->new RuntimeException("Id não encontrado"));
  }

  public void fazerPedido(){
    //chama transacao - chama pedido - chama produto
  }

}

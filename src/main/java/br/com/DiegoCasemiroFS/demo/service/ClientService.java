package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Client;
import br.com.DiegoCasemiroFS.demo.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  ClienteRepository clienteRepository;

  public Client findById(Long id){
    return clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Client> listCliente(){
    return clienteRepository.findAll();
  }

  public Client createCliente(Client client){
    clienteRepository.save(client);
    return client;
  }

  public Client updateCliente(Long id, Client client){
    return clienteRepository.findById(id)
        .map(f -> {
          client.setId(f.getId());
          clienteRepository.save(client);
          return client;
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

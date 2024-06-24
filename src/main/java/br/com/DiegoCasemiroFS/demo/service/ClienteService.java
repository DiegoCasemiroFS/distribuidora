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

  public List<Cliente> listClient(){
    return clienteRepository.findAll();
  }

  public Cliente creteClient(Cliente cliente){
    clienteRepository.save(cliente);
    return cliente;
  }

  public Cliente updateClient(Long id, Cliente cliente){
    return clienteRepository.findById(id)
        .map(f -> {
          cliente.setId(f.getId());
          clienteRepository.save(cliente);
          return cliente;
        }).orElseThrow(()->new RuntimeException("Id não encontrado"));
  }

  public void deleteClient(Long id){
    clienteRepository.findById(id)
        .map(f->{
          clienteRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(()->new RuntimeException("Id não encontrado"));
  }

}

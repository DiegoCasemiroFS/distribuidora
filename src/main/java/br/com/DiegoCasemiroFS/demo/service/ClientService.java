package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Client;
import br.com.DiegoCasemiroFS.demo.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  ClientRepository clientRepository;

  public Client findById(Long id){
    return clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Client> findall(){
    return clientRepository.findAll();
  }

  public Client creteClient(Client client){
    clientRepository.save(client);
    return client;
  }

  public Client updateClient(Long id, Client client){
    return clientRepository.findById(id)
        .map(f -> {
          client.setId(f.getId());
          clientRepository.save(client);
          return client;
        }).orElseThrow(()->new RuntimeException("Id não encontrado"));
  }

  public void deleteClient(Long id){
    clientRepository.findById(id)
        .map(f->{
          clientRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(()->new RuntimeException("Id não encontrado"));
  }

}

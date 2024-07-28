package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Client;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.ClientDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ClientNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  ClientRepository clientRepository;

  public Client findById(Long id){
    return clientRepository.findById(id)
        .orElseThrow(ClientNotFoundException::new);
  }

  public List<Client> findAllClients(){
    return clientRepository.findAll();
  }

  public Client createClient(Client client){
    return clientRepository.save(client);
  }

  public Client updateClient(Long id, ClientDto clientDto){
    return clientRepository.findById(id)
        .map(client -> {
          client.setAddress(clientDto.getAddress());
          client.setEmail(clientDto.getEmail());
          client.setPhone(clientDto.getPhone());
          return clientRepository.save(client);
        }).orElseThrow(ClientNotFoundException::new);
  }

  public void deleteClient(Long id){
    clientRepository.deleteById(id);
  }
}

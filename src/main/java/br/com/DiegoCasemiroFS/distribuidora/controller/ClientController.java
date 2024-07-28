package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Client;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.ClientDto;
import br.com.DiegoCasemiroFS.distribuidora.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/findById/{id}")
  public Client findById(@PathVariable Long id){
    return clientService.findById(id);
  }

  @GetMapping("/findAll")
  public List<Client> findAllClients(){
    return clientService.findAllClients();
  }

  @PostMapping("/create")
  public Client createClient(@RequestBody Client client){
    return clientService.createClient(client);
  }

  @PutMapping("/update/{id}")
  public Client updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto){
    return clientService.updateClient(id, clientDto);
  }

  @DeleteMapping("/delete")
  public void deleteClient(@PathVariable Long id){
    clientService.deleteClient(id);
  }
}

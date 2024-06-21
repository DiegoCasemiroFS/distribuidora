package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Client;
import br.com.DiegoCasemiroFS.demo.service.ClientService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/client")
public class ClientController {

  private ClientService clientService;

  @GetMapping
  public Client findById(@PathVariable Long id){
    return clientService.findById(id);
  }

  @GetMapping
  public List<Client> findAll(){
    return clientService.findall();
  }

  @PostMapping
  public Client createClient(@RequestBody Client client){
    return clientService.creteClient(client);
  }

  @PutMapping
  public Client updateClient(@PathVariable Long id, @RequestBody Client client){
    return clientService.updateClient(id, client);
  }

  @DeleteMapping
  public void deleteClient(@PathVariable Long id){
    clientService.deleteClient(id);
  }

}

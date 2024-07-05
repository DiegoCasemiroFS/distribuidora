package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Client;
import br.com.DiegoCasemiroFS.demo.service.ClienteService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/{id}")
  public Client findById(@PathVariable Long id){
    return clienteService.findById(id);
  }

  @GetMapping
  public List<Client> findAll(){
    return clienteService.listCliente();
  }

  @PostMapping
  public Client createCliente(@RequestBody Client client){
    return clienteService.createCliente(client);
  }

  @PutMapping
  public Client updateCliente(@PathVariable Long id, @RequestBody Client client){
    return clienteService.updateCliente(id, client);
  }

  @DeleteMapping
  public void deleteCliente(@PathVariable Long id){
    clienteService.deleteCliente(id);
  }

}

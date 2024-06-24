package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Cliente;
import br.com.DiegoCasemiroFS.demo.service.ClienteService;
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
public class ClienteController {

  private ClienteService clienteService;

  @GetMapping
  public Cliente findById(@PathVariable Long id){
    return clienteService.findById(id);
  }

  @GetMapping
  public List<Cliente> findAll(){
    return clienteService.listClient();
  }

  @PostMapping
  public Cliente createClient(@RequestBody Cliente cliente){
    return clienteService.creteClient(cliente);
  }

  @PutMapping
  public Cliente updateClient(@PathVariable Long id, @RequestBody Cliente cliente){
    return clienteService.updateClient(id, cliente);
  }

  @DeleteMapping
  public void deleteClient(@PathVariable Long id){
    clienteService.deleteClient(id);
  }

}

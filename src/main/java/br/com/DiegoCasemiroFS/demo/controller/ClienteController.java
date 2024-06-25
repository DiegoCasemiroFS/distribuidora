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
@RequestMapping("v1/api/cliente")
public class ClienteController {

  private ClienteService clienteService;

  @GetMapping
  public Cliente findById(@PathVariable Long id){
    return clienteService.findById(id);
  }

  @GetMapping
  public List<Cliente> findAll(){
    return clienteService.listCliente();
  }

  @PostMapping
  public Cliente createCliente(@RequestBody Cliente cliente){
    return clienteService.createCliente(cliente);
  }

  @PutMapping
  public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
    return clienteService.updateCliente(id, cliente);
  }

  @DeleteMapping
  public void deleteCliente(@PathVariable Long id){
    clienteService.deleteCliente(id);
  }

}

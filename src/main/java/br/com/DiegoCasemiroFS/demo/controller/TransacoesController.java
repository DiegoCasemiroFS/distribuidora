package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Transactions;
import br.com.DiegoCasemiroFS.demo.service.TransacoesService;
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
@RequestMapping("v1/api/transacoes")
public class TransacoesController {

  @Autowired
  TransacoesService transacoesService;

  @GetMapping("/{id}")
  public Transactions findById(@PathVariable Long id){
    return transacoesService.findById(id);
  }

  @GetMapping
  public List<Transactions> listTransacoes(){
    return transacoesService.listTransacoes();
  }

  @PostMapping
  public Transactions createTransacoes(@RequestBody Transactions transactions){
    return transacoesService.createTransacoes(transactions);
  }

  @PutMapping
  public Transactions updateTransacoes(@PathVariable Long id, @RequestBody Transactions transactions){
    return transacoesService.updateTransacoes(id, transactions);
  }

  @DeleteMapping
  public void deleteTransacoes(@PathVariable Long id){
    transacoesService.deleteTransacoes(id);
  }

}
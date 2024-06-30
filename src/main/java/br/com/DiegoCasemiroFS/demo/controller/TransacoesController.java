package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Transacoes;
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

  @GetMapping
  public Transacoes findById(@PathVariable Long id){
    return transacoesService.findById(id);
  }

  @GetMapping
  public List<Transacoes> listTransacoes(){
    return transacoesService.listTransacoes();
  }

  @PostMapping
  public Transacoes createTransacoes(@RequestBody Transacoes transacoes){
    return transacoesService.createTransacoes(transacoes);
  }

  @PutMapping
  public Transacoes updateTransacoes(@PathVariable Long id, @RequestBody Transacoes transacoes){
    return transacoesService.updateTransacoes(id, transacoes);
  }

  @DeleteMapping
  public void deleteTransacoes(@PathVariable Long id){
    transacoesService.deleteTransacoes(id);
  }

}
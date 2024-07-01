package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Pagamento;
import br.com.DiegoCasemiroFS.demo.service.PagamentoService;
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
@RequestMapping("v1/api/pagamento")
public class PagamentoController {

  @Autowired
  PagamentoService pagamentoService;

  @GetMapping("/{id}")
  public Pagamento findById(@PathVariable Long id){
    return pagamentoService.findById(id);
  }

  @GetMapping
  public List<Pagamento> listPagamento(){
    return pagamentoService.listPagamento();
  }

  @PostMapping
  public Pagamento createPagamento(@RequestBody Pagamento pagamento){
    return pagamentoService.createPagamento(pagamento);
  }

  @PutMapping
  public Pagamento updatePagamento(@PathVariable Long id, @RequestBody Pagamento pagamento){
    return pagamentoService.updatePagamento(id, pagamento);
  }

  @DeleteMapping
  public void deletePagamento(@PathVariable Long id){
    pagamentoService.deletePagamento(id);
  }

}

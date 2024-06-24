package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Fornecedor;
import br.com.DiegoCasemiroFS.demo.service.FornecedorService;
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
@RequestMapping("v1/api/fornecedor")
public class FornecedorController {

  @Autowired
  FornecedorService fornecedorService;

  @GetMapping
  public Fornecedor findById(@PathVariable Long id){
    return fornecedorService.findById(id);
  }

  @GetMapping
  public List<Fornecedor> listFornecedor(){
    return fornecedorService.listFornecedor();
  }

  @PostMapping
  public Fornecedor createFornecedor(@RequestBody Fornecedor fornecedor){
    return fornecedorService.createFornecedor(fornecedor);
  }

  @PutMapping
  public Fornecedor updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor){
    return fornecedorService.updateFornecedor(id, fornecedor);
  }

  @DeleteMapping
  public void deleteFornecedor(@PathVariable Long id){
    fornecedorService.deleteFornecedor(id);
  }
}
package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Supplier;
import br.com.DiegoCasemiroFS.demo.service.SupplierService;
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
  SupplierService supplierService;

  @GetMapping("/{id}")
  public Supplier findById(@PathVariable Long id){
    return supplierService.findById(id);
  }

  @GetMapping
  public List<Supplier> listFornecedor(){
    return supplierService.listFornecedor();
  }

  @PostMapping
  public Supplier createFornecedor(@RequestBody Supplier supplier){
    return supplierService.createFornecedor(supplier);
  }

  @PutMapping
  public Supplier updateFornecedor(@PathVariable Long id, @RequestBody Supplier supplier){
    return supplierService.updateFornecedor(id, supplier);
  }

  @DeleteMapping
  public void deleteFornecedor(@PathVariable Long id){
    supplierService.deleteFornecedor(id);
  }
}
package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Supplier;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.SupplierDto;
import br.com.DiegoCasemiroFS.distribuidora.service.SupplierService;
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
@RequestMapping("/supplier")
public class SupplierController {

  @Autowired
  SupplierService supplierService;

  @GetMapping("/findById/{id}")
  public Supplier findById(@PathVariable Long id){
    return supplierService.findById(id);
  }

  @GetMapping("/findAll")
  public List<Supplier> listAllSuppliers(){
    return supplierService.listAllSuppliers();
  }

  @PostMapping("/create")
  public Supplier createSupplier(@RequestBody Supplier supplier){
    return supplierService.createSupplier(supplier);
  }

  @PutMapping("/update")
  public Supplier updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto){
    return supplierService.updateSupplier(id, supplierDto);
  }

  @DeleteMapping("/delete")
  public void deleteSupplier(@PathVariable Long id){
    supplierService.deleteSupplier(id);
  }
}
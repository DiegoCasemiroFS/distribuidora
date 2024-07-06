package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Supplier;
import br.com.DiegoCasemiroFS.demo.repository.SupplierRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  @Autowired
  SupplierRepository supplierRepository;

  public Supplier findById(Long id){
    return supplierRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

  public List<Supplier> listFornecedor(){
    return supplierRepository.findAll();
  }

  public Supplier createFornecedor(Supplier supplier){
    supplierRepository.save(supplier);
    return supplier;
  }

  public Supplier updateFornecedor(Long id, Supplier supplier){
    return supplierRepository.findById(id)
        .map(f -> {
          supplier.setId(f.getId());
          supplierRepository.save(supplier);
          return supplier;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

  public void deleteFornecedor(Long id){
    supplierRepository.findById(id)
        .map(f -> {
          supplierRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}

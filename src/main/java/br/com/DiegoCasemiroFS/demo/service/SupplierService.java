package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Supplier;
import br.com.DiegoCasemiroFS.demo.repository.FornecedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  @Autowired
  FornecedorRepository fornecedorRepository;

  public Supplier findById(Long id){
    return fornecedorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

  public List<Supplier> listFornecedor(){
    return fornecedorRepository.findAll();
  }

  public Supplier createFornecedor(Supplier supplier){
    fornecedorRepository.save(supplier);
    return supplier;
  }

  public Supplier updateFornecedor(Long id, Supplier supplier){
    return fornecedorRepository.findById(id)
        .map(f -> {
          supplier.setId(f.getId());
          fornecedorRepository.save(supplier);
          return supplier;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

  public void deleteFornecedor(Long id){
    fornecedorRepository.findById(id)
        .map(f -> {
          fornecedorRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}

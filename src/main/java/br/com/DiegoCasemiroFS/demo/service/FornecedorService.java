package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Fornecedor;
import br.com.DiegoCasemiroFS.demo.repository.FornecedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

  @Autowired
  FornecedorRepository fornecedorRepository;

  public Fornecedor findById(Long id){
    return fornecedorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

  public List<Fornecedor> listFornecedor(){
    return fornecedorRepository.findAll();
  }

  public Fornecedor createFornecedor(Fornecedor fornecedor){
    fornecedorRepository.save(fornecedor);
    return fornecedor;
  }

  public Fornecedor updateFornecedor(Long id, Fornecedor fornecedor){
    return fornecedorRepository.findById(id)
        .map(f -> {
          fornecedor.setId(f.getId());
          fornecedorRepository.save(fornecedor);
          return fornecedor;
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

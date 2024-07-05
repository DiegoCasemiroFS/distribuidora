package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Transactions;
import br.com.DiegoCasemiroFS.demo.repository.TransacoesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacoesService {

  @Autowired
  TransacoesRepository transacoesRepository;

  public Transactions findById(Long id){
    return transacoesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Transactions> listTransacoes(){
    return transacoesRepository.findAll();
  }

  public Transactions createTransacoes(Transactions transactions){
    transacoesRepository.save(transactions);
    return transactions;
  }

  public Transactions updateTransacoes(Long id, Transactions transactions){
    return transacoesRepository.findById(id)
        .map(f -> {
          transactions.setId(f.getId());
          return transactions;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deleteTransacoes(Long id){
     transacoesRepository.findById(id)
        .map(f -> {
          transacoesRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

}
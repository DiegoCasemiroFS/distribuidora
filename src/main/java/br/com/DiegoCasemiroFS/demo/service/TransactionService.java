package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Transaction;
import br.com.DiegoCasemiroFS.demo.repository.TransacoesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  TransacoesRepository transacoesRepository;

  public Transaction findById(Long id){
    return transacoesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Transaction> listTransacoes(){
    return transacoesRepository.findAll();
  }

  public Transaction createTransacoes(Transaction transaction){
    transacoesRepository.save(transaction);
    return transaction;
  }

  public Transaction updateTransacoes(Long id, Transaction transaction){
    return transacoesRepository.findById(id)
        .map(f -> {
          transaction.setId(f.getId());
          return transaction;
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
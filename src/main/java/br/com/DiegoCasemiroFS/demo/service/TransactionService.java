package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Transaction;
import br.com.DiegoCasemiroFS.demo.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  TransactionRepository transactionRepository;

  public Transaction findById(Long id){
    return transactionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Transaction> listTransacoes(){
    return transactionRepository.findAll();
  }

  public Transaction createTransacoes(Transaction transaction){
    transactionRepository.save(transaction);
    return transaction;
  }

  public Transaction updateTransacoes(Long id, Transaction transaction){
    return transactionRepository.findById(id)
        .map(f -> {
          transaction.setId(f.getId());
          return transaction;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deleteTransacoes(Long id){
     transactionRepository.findById(id)
        .map(f -> {
          transactionRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

}
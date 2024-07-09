package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Transaction;
import br.com.DiegoCasemiroFS.distribuidora.service.TransactionService;
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
@RequestMapping("v1/api/transacoes")
public class TransactionController {

  @Autowired
  TransactionService transactionService;

  @GetMapping("/{id}")
  public Transaction findById(@PathVariable Long id){
    return transactionService.findById(id);
  }

  @GetMapping
  public List<Transaction> listTransacoes(){
    return transactionService.findAllTransactions();
  }

  @PostMapping
  public Transaction createTransacoes(@RequestBody Transaction transaction){
    return transactionService.createTransaction(transaction);
  }

  @PutMapping
  public Transaction updateTransacoes(@PathVariable Long id, @RequestBody Transaction transaction){
    return transactionService.updateTransaction(id, transaction);
  }

  @DeleteMapping
  public void deleteTransacoes(@PathVariable Long id){
    transactionService.deleteTransaction(id);
  }

}
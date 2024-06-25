package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Transacoes;
import br.com.DiegoCasemiroFS.demo.repository.TransacoesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacoesService {

  @Autowired
  TransacoesRepository transacoesRepository;

  public Transacoes findById(Long id){
    return transacoesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Transacoes> listTransacoes(){
    return transacoesRepository.findAll();
  }

  public Transacoes createTransacoes(Transacoes transacoes){
    transacoesRepository.save(transacoes);
    return transacoes;
  }

  public Transacoes updateTransacoes(Long id, Transacoes transacoes){
    return transacoesRepository.findById(id)
        .map(f -> {
          transacoes.setId(f.getId());
          return transacoes;
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
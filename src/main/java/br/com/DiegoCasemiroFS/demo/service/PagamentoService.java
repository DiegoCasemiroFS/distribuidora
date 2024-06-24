package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Pagamento;
import br.com.DiegoCasemiroFS.demo.repository.PagamentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

  @Autowired
  PagamentoRepository pagamentoRepository;

  public Pagamento findById(Long id){
    return pagamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

  public List<Pagamento> listPagamento(){
    return pagamentoRepository.findAll();
  }

  public Pagamento createPagamento(Pagamento pagamento){
    pagamentoRepository.save(pagamento);
    return pagamento;
  }

  public Pagamento updatePagamento (Long id, Pagamento pagamento){
    return pagamentoRepository.findById(id)
        .map(f -> {
          pagamento.setId(f.getId());
          pagamentoRepository.save(pagamento);
          return pagamento;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

  public void deletePagamento(Long id){
    pagamentoRepository.findById(id)
        .map(f -> {
          pagamentoRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }
}

package br.com.DiegoCasemiroFS.demo.repository;

import br.com.DiegoCasemiroFS.demo.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}

package br.com.DiegoCasemiroFS.demo.repository;

import br.com.DiegoCasemiroFS.demo.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transactions, Long> {

}

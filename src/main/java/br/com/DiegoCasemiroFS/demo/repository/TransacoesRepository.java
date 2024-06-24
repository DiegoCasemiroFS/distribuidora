package br.com.DiegoCasemiroFS.demo.repository;

import br.com.DiegoCasemiroFS.demo.entity.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

}

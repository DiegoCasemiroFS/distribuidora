package br.com.DiegoCasemiroFS.distribuidora.repository;

import br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}

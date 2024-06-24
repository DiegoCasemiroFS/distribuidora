package br.com.DiegoCasemiroFS.demo.repository;

import br.com.DiegoCasemiroFS.demo.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}

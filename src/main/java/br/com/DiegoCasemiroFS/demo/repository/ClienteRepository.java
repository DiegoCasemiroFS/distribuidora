package br.com.DiegoCasemiroFS.demo.repository;

import br.com.DiegoCasemiroFS.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

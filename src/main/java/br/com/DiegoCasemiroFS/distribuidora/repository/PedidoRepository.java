package br.com.DiegoCasemiroFS.distribuidora.repository;

import br.com.DiegoCasemiroFS.distribuidora.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

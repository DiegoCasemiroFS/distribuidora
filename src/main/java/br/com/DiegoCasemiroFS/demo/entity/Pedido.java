package br.com.DiegoCasemiroFS.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date dataPedido;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "suplier_id")
  private Fornecedor suplier;

  @OneToOne
  @JoinColumn(name = "payment_id")
  private Pagamento pagamento;

}

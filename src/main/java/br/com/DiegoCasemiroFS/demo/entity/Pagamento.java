package br.com.DiegoCasemiroFS.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private String boleto;

  private String pix;

  private boolean realizado;

  @OneToOne
  @JoinColumn(name = "request_id")
  private Pedido pedido;

}

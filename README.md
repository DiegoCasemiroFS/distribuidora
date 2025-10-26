# API Distribuidora

API de gestão de Vendas, Produtos e Usuários de uma distribuidora.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- PostgreSQL

## Configuração do Ambiente

1. **Clone o repositório:**
```bash
git clone https://github.com/DiegoCasemiroFS/distribuidora.git
```

## Diagrama de classes:

```mermaid
classDiagram
    class Movimentacao {
        +Long id
        +Usuario usuarioId
        +String nomeUsuario
        +Produto produtoId
        +String nomeProduto
        +Integer quantidade
        +Integer estoqueAtual
        +BigDecimal valorUnitario
        +BigDecimal valorTotal
        +Integer tipoMovimentacao
        +LocalDateTime dataPedido

        +procuraPorId(Long id)
        +listaTodas()
        +vendaCliente(MovimentacaoRequestDto movimentacaoRequestDto)
        +compraFornecedor(MovimentacaoRequestDto movimentacaoRequestDto)
        +deletaMovimentacao(Long id)
    }

    class Produto {
        +Long id
        +String nome
        +TipoProduto tipoProduto
        +Integer quantidade
        +BigDecimal preco

        +procuraPorId(Long id)
        +listaTodos()
        +cadastraProduto(Produto produto)
        +alteraPreco(Long id, ProdutoRequestDto precoRequestDto)
        +alteraEstoque(Long id, ProdutoRequestDto precoRequestDto)
        +deletaProduto(Long id)
    }

    class Usuario {
        +Long id
        +String nome
        +String email
        +String senha
        +String contato
        +Endereco endereco
        +TipoUsuario tipoUsuario
        +boolean admin

        +procuraPorId(Long id)
        +procuraPorEmail(String email)
        +listaTodos()
        +cadastraUsuario(User user)
        +login(LoginRequestDto requestDto)
        +alteraUsuario(Long id, UserRequestDto userRequestDto)
        +deletaUsuario(Long id)
    }

    class Endereco {
        +String rua
        +Integer numero
        +String complemento
        +String bairro
        +String cidade
        +String estado
        +String cep
    }

    Usuario "1" --> "n" Movimentacao
    Produto "1" --> "n" Movimentacao
    Usuario "1" --> "1" Endereco
```

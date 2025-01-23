# API Distribuidora

Esta é a API para a aplicação Distribuidora, desenvolvida em Java com Spring Boot.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- H2 Database

## Configuração do Ambiente

1. **Clone o repositório:**
```bash
git clone https://github.com/DiegoCasemiroFS/distribuidora.git
```

## Documentação

```bash
http://localhost:8080/v3/api-docs
```

```bash
http://localhost:8080/swagger-ui/index.html
```

## Diagrama de classes:

```mermaid
classDiagram
    class Movimentacao {
        +Long id
        +Usuario usuarioId
        +Produto produtoId
        +Integer quantidade
        +Integer estoque
        +LocalDate dataPedido

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
        +alteraPreco(Long id, ProdutoRequestDto produtoRequestDto)
        +alteraEstoque(Long id, ProdutoRequestDto produtoRequestDto)
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
        +cadastraUsuario(Usuario usuario)
        +login(LoginRequestDto requestDto)
        +alteraUsuario(Long id, UsuarioRequestDto usuarioRequestDto)
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

    Usuario "1" --> "n" Pedido
    Produto "1" --> "n" Pedido
    Usuario "1" --> "1" Endereco
```

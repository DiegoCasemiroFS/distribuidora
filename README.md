# API Distribuidora

Esta é a API para a aplicação Distribuidora, desenvolvida em Java com Spring Boot.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- PostgreSQL

## Configuração do Ambiente

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/DiegoCasemiroFS/distribuidora.git
   cd distribuidora

## Documentação

```bash
http://localhost:8080/v3/api-docs
```

```bash
http://localhost:8080/swagger-ui/index.html
```

**Diagrama de classes:**

```mermaid
classDiagram   
    class Orders {
        +Long id;
        +Users userId;
        +Product productId;
        +Integer quantity;
        +LocalDate orderDate;
        
        +findOrderById(Long id)
        +findAllOrders()
        +createOrder(OrderRequestDto orderRequestDto)
        +updateOrder(Long id, OrderRequestDto orderRequestDto)
        +deleteOrder(Long id)
    }
    
    class Product {
        +Long id;
        +String name;
        +String brand;
        +ProductType productType
        +BigDecimal price;
        
        +findProductById(Long id)
        +findAllProducts()
        +createProduct(Product product)
        +updateProduct(Long id, ProductRequestDto productRequestDto)
        +deleteProduct(Long id)
    }
    
    class Users {
        +Long id;
        +String name;
        +String email;
        +String password;
        +String address;
        +String phone;
        +boolean admin;
        +UserType userType
        
        +findUserById(Long id)
        +findAllUsers()
        +createUser(Users users)
        +updateUser(Long id, UserRequestDto userRequestDto)
        +deleteUser(Long id)
    }

    Users "1" --> "n" Orders
    Product "1" --> "n" Orders
```

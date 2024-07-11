***API DISTRIBUIDORA***

**Função:**
- Facilitar o gerenciamento dos Clientes e Fornecedores de uma Distribuidora

**Diagrama de classes:**

```mermaid
classDiagram
    class Client {
        +Long id;
        +String name;
        +String address;
        +String email;
        +String phone;
        +List<Order> orders;
        
        +findById(Long id)
        +findAllClients()
        +createCLient(Client client)
        +updateClient(Long id, Client client)
        +deleteClient(Long id)
    }
    
    class Supplier {
        +Long id;
        +String name;
        +String address;
        +String email;
        +String phone;
        +List<Product> products;
        
        +findById(Long id)
        +listAllSuppliers()
        +createSupplier(Supplier supplier)
        +updateSupplier(Long id, Supplier supplier)
        +deleteSupplier(Long id)
    }
    
    class Order {
        +Long id;
        +Client client;
        +Supplier supplier;
        +LocalDate orderDate;
        +BigDecimal total;
        +OrderStatus status;
        +List<OrderItem> itens;
        
        +createOrder(OrderDto orderDto)
        +bringComplete(Long id)
        +updateStatus(Long id, UpdateOrderDtoStatus updateOrderDtoStatus)
    }
    
    Class OrderItem {

        +Long id;
        +Order order;
        +Product product;
        +Integer quantity;
    }
    
    class Product {
        +Long id;
        +String name;
        +BigDecimal price;
        
        +findById(Long id)
        +findAll()
        +createProduct(Product product)
        +updateProduct(Long id, Product product)
        +deleteProduct(Long id)
    }

    Client "1" --> "n" Order
    Supplier "1" --> "n" Order
    OrderItem "n" --> "1" Order
    OrderItem "n" --> "1" Product
    Supplier "n" --> "n" Product

```

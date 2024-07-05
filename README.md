***API DISTRIBUIDORA***

**Função:**
- Facilitar o gerenciamento dos Clientes e Fornecedores de uma Distribuidora

**Diagrama de classes:**

```mermaid
classDiagram
    class Client {
        +Long id
        +String name
        +String address
        +String email
        +String phone
        +List<Order> orders
        
        +createOrder(Order order)
        +updateDetails(String name, String address, String email, String phone)
        +listOrders(): List<Order>
    }
    
    class Supplier {
        +Long id
        +String name
        +String address
        +String email
        +String phone
        +List<Product> suppliedProducts
        
        +addProduct(Product product)
        +updateDetails(String name, String address, String email, String phone)
        +listProducts(): List<Product>
    }
    
    class Order {
        +Long id
        +Date date
        +Client client
        +Supplier supplier
        +List<Product> products
        +Double totalValue
        
        +calculateTotalValue(): double
        +addProduct(Product product)
        +removeProduct(Product product)
        +finalizeOrder()
    }
    
    class Product {
        +Long id
        +String name
        +String brand
        +String group
        +Double price
        +Integer stockQuantity
        
        +updateStock(int quantity)
        +updatePrice(double price)
    }
    
    class Transactions {
        +Long id
        +Date date
        +String type
        +Double value
        +Order order
        
        +recordTransaction(Order order, String type, double value)
    }

    Client "1" --> "n" Order
    Supplier "1" --> "n" Order
    Order "n" --> "n" Product
    Product "1" --> "n" Supplier
    Order "1" --> "1" Transactions


```

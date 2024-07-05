***API DISTRIBUIDORA***

**Função:**
- Facilitar o gerenciamento dos Clientes e Fornecedores de uma Distribuidora

**Diagrama de classes:**

```mermaid
classDiagram
    class Customer {
        +int id
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
        +int id
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
        +int id
        +Date date
        +Customer customer
        +List<Product> products
        +double totalValue
        
        +calculateTotalValue(): double
        +addProduct(Product product)
        +removeProduct(Product product)
        +finalizeOrder()
    }
    
    class Product {
        +int id
        +String name
        +String description
        +double price
        +int stockQuantity
        
        +updateStock(int quantity)
        +updatePrice(double price)
    }
    
    class Transactions {
        +int id
        +Date date
        +String type
        +double value
        +Order order
        
        +recordTransaction(Order order, String type, double value)
    }

    Customer "1" --> "*" Order
    Order "*" --> "*" Product
    Product "1" --> "*" Supplier
    Order "1" --> "1" Transactions

```

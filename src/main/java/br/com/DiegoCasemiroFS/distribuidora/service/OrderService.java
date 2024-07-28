package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Client;
import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.Supplier;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ClientNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.OrderNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.SupplierNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ClientRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.OrderRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public Order findById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    public Order createOrder(OrderDto orderDto){
        Client client = clientRepository.findById(orderDto.getClientId())
                .orElseThrow(ClientNotFoundException::new);
        Supplier supplier = supplierRepository.findById(orderDto.getSupplierId())
                .orElseThrow(SupplierNotFoundException::new);
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        Order order = new Order();
        order.setClient(client);
        order.setSupplier(supplier);
        order.setProduct(product);
        order.setQuantity(orderDto.getQuantity());
        order.setStatus(orderDto.getStatus());

        return order;
    }

    public Order updateClientOrder(Long id, OrderDto orderDto){
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
        Client client = clientRepository.findById(orderDto.getClientId())
                .orElseThrow(ClientNotFoundException::new);
        Supplier supplier = supplierRepository.findById(orderDto.getSupplierId())
                .orElseThrow(SupplierNotFoundException::new);
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        if(orderDto.getClientId().equals(order.getClient().getId())){
            order.setSupplier(supplier);
            order.setProduct(product);
            order.setQuantity(orderDto.getQuantity());
            order.setOrderDate(orderDto.getOrderDate());
            order.setStatus(orderDto.getStatus());

            return orderRepository.save(order);
        }
        throw new OrderNotFoundException();
    }

    public Order updateSupplierOrder(Long id, OrderDto orderDto){
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
        Client client = clientRepository.findById(orderDto.getClientId())
                .orElseThrow(ClientNotFoundException::new);
        Supplier supplier = supplierRepository.findById(orderDto.getSupplierId())
                .orElseThrow(SupplierNotFoundException::new);
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        if(orderDto.getSupplierId().equals(order.getSupplier().getId())){
            order.setClient(client);
            order.setProduct(product);
            order.setQuantity(orderDto.getQuantity());
            order.setOrderDate(orderDto.getOrderDate());
            order.setStatus(orderDto.getStatus());

            return orderRepository.save(order);
        }
        throw new OrderNotFoundException();
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}

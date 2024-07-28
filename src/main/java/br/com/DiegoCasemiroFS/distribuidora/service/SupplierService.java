package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Supplier;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.SupplierDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.SupplierNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {


    @Autowired
    SupplierRepository supplierRepository;

    public Supplier findById(Long id){
        return supplierRepository.findById(id)
                .orElseThrow(SupplierNotFoundException::new);
    }

    public List<Supplier> listAllSuppliers(){
        return supplierRepository.findAll();
    }

    public Supplier createSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, SupplierDto supplierDto) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplier.setEmail(supplierDto.getEmail());
                    supplier.setAddress(supplierDto.getAddress());
                    supplier.setPhone(supplierDto.getPhone());
                    return supplier;
                }).orElseThrow(SupplierNotFoundException::new);
    }

    public void deleteSupplier(Long id){
        supplierRepository.deleteById(id);
    }
}

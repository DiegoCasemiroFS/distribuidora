package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.EstoqueRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.PrecoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/procuraPorId/{id}")
    public ResponseEntity<Produto> procuraPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.procuraPorId(id));
    }

    @GetMapping("/listaTodos")
    public ResponseEntity<List<Produto>> listaTodos() {
        return ResponseEntity.ok(produtoService.listaTodos());
    }

    @PostMapping("/cadastraProduto")
    public ResponseEntity<Produto> cadastraProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.cadastraproduto(produto));
    }

    @PutMapping("/alteraPreco/{id}")
    public ResponseEntity<Produto> alteraPreco(@PathVariable Long id, @RequestBody PrecoRequestDto precoRequestDto) {
        return ResponseEntity.ok(produtoService.alteraPreco(id, precoRequestDto));
    }

    @PutMapping("/alteraEstoque/{id}")
    public ResponseEntity<Produto> alteraEstoque(@PathVariable Long id, @RequestBody EstoqueRequestDto estoqueRequestDto) {
        return ResponseEntity.ok(produtoService.alteraEstoque(id, estoqueRequestDto));
    }

    @DeleteMapping("/deletaProduto/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable Long id) {
        produtoService.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
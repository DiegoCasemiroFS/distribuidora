package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao.Movimentacao;
import br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao.MovimentacaoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao.MovimentacaoResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.service.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @GetMapping("/procuraPorId/{id}")
    public ResponseEntity<Movimentacao> procuraPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoService.procuraPorId(id));
    }

    @GetMapping("/listaTodas")
    public ResponseEntity<List<Movimentacao>> listaTodas() {
        return ResponseEntity.ok(movimentacaoService.listaTodas());
    }

    @PostMapping("/vendaCliente")
    public ResponseEntity<MovimentacaoResponseDto> vendaCliente(@RequestBody MovimentacaoRequestDto movimentacaoRequestDto) {
        return ResponseEntity.ok(movimentacaoService.vendaCliente(movimentacaoRequestDto));
    }

    @PostMapping("/compraFornecedor")
    public ResponseEntity<MovimentacaoResponseDto> compraFornecedor(@RequestBody MovimentacaoRequestDto movimentacaoRequestDto) {
        return ResponseEntity.ok(movimentacaoService.compraFornecedor(movimentacaoRequestDto));
    }

    @DeleteMapping("/deletaMovimentacao/{id}")
    public ResponseEntity<Void> deletaMovimentacao(@PathVariable Long id) {
        movimentacaoService.deletaMovimentacao(id);
        return ResponseEntity.noContent().build();
    }
}
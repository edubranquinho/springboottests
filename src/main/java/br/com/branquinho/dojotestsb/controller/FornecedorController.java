package br.com.branquinho.dojotestsb.controller;

import br.com.branquinho.dojotestsb.model.Fornecedor;
import br.com.branquinho.dojotestsb.service.FornecedorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public Fornecedor novoFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.novoFornecedor(fornecedor);
    }
}

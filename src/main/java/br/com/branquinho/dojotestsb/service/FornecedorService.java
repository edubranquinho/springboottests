package br.com.branquinho.dojotestsb.service;

import br.com.branquinho.dojotestsb.model.Fornecedor;
import br.com.branquinho.dojotestsb.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public Fornecedor novoFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }
}

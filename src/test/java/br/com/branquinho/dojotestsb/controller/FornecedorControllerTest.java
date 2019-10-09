package br.com.branquinho.dojotestsb.controller;

import br.com.branquinho.dojotestsb.model.Fornecedor;
import br.com.branquinho.dojotestsb.service.FornecedorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FornecedorController.class)
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FornecedorService fornecedorService;

    @Test
    public void deveCriarNovoFornecedor() throws Exception {
        Fornecedor fornecedorSalvo = new Fornecedor();
        fornecedorSalvo.setId(2l);
        fornecedorSalvo.setCnpj(12312312);
        fornecedorSalvo.setNome("Ambev");

        when(fornecedorService.novoFornecedor(any(Fornecedor.class))).thenReturn(fornecedorSalvo);

        Fornecedor fornecedorASalvar = new Fornecedor();
        fornecedorASalvar.setCnpj(12312312);
        fornecedorASalvar.setNome("Ambev");

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String fornecedorJson = objectWriter.writeValueAsString(fornecedorASalvar);

        String jsonEsperado = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(fornecedorSalvo);

        mockMvc.perform(MockMvcRequestBuilders.post("/fornecedor")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8).content(fornecedorJson))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonEsperado));

    }

}
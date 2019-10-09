package br.com.branquinho.dojotestsb.controller;

import br.com.branquinho.dojotestsb.model.Fornecedor;
import br.com.branquinho.dojotestsb.service.FornecedorService;
import br.com.branquinho.dojotestsb.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
//@SpringBootTest
//@AutoConfigureMockMvc
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FornecedorService fornecedorService;

    @Test
    public void deveCriarNovoFornecedor() throws Exception {
        Fornecedor fornecedorEsperado = new Fornecedor();
        fornecedorEsperado.setId((long) 1);
        fornecedorEsperado.setCnpj(12312312);
        fornecedorEsperado.setNome("Ambev");

        when(fornecedorService.novoFornecedor(any(Fornecedor.class))).thenReturn(fornecedorEsperado);

        Fornecedor fornecedorASalvar = new Fornecedor();
        fornecedorASalvar.setCnpj(12312312);
        fornecedorASalvar.setNome("Ambev");

        mockMvc.perform(MockMvcRequestBuilders.post("/fornecedor")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8).content(JsonUtils.toJson(fornecedorASalvar)))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtils.toJson(fornecedorEsperado)));
    }

    @Test
    public void naoDeveSerPermitidoFornecedorSemNome() throws Exception {
        Fornecedor fornecedorSemNome = new Fornecedor();
        fornecedorSemNome.setId((long) 1);
        fornecedorSemNome.setCnpj(12312312);

        mockMvc.perform(MockMvcRequestBuilders.post("/fornecedor")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8).content(JsonUtils.toJson(fornecedorSemNome)))
                .andExpect(status().isBadRequest());
    }

}
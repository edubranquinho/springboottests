package br.com.branquinho.dojotestsb;

import br.com.branquinho.dojotestsb.controller.HelloPapoRetoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
//@SpringBootTest (https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)
@WebMvcTest(controllers = HelloPapoRetoController.class) //https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/web/servlet/WebMvcTest.html
//controllers = Specifies the controllers to test. May be left blank if all @Controller beans should be added to the application context.
public class DojotestsbApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloWorld() throws Exception {
        //https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html#perform-org.springframework.test.web.servlet.RequestBuilder-
        mockMvc
                .perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World"));
    }

}

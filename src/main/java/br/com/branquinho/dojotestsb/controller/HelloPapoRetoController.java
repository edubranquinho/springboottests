package br.com.branquinho.dojotestsb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.TableGenerator;

@RestController
public class HelloPapoRetoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}

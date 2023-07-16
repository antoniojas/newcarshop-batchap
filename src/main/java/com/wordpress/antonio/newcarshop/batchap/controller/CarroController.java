package com.wordpress.antonio.newcarshop.batchap.controller;

import com.wordpress.antonio.newcarshop.batchap.model.Carro;
import com.wordpress.antonio.newcarshop.batchap.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/home")
    public String mensagem(){
        return "<h2>Bem vindo a API BATCH - New Car Shop</h2>";
    }

    @GetMapping("/batch")
    public String execute(){
        return carroService.batchExecute().toString();
    }

    @GetMapping("/carros")
    public List<Carro> todos(){
        return carroService.findAll();
    }
}

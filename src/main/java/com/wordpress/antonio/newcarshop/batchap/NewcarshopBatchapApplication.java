package com.wordpress.antonio.newcarshop.batchap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //processo agendado
public class NewcarshopBatchapApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewcarshopBatchapApplication.class, args);
	}

}

package com.lundero.m1;

import controller.GenericController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.PizzaService;
import util.RestClientUtil;

@SpringBootApplication
//@Configuration
//@ComponentScan({"com.lundero.m1"})
@EntityScan("model")
@ComponentScan(basePackageClasses = {GenericController.class,
									RestClientUtil.class,
									PizzaService.class})
@EnableJpaRepositories("repository")
public class M1Application {

	public static void main(String[] args) {
		SpringApplication.run(M1Application.class, args);
	}

}

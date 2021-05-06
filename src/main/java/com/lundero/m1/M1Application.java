package com.lundero.m1;

import controller.GenericController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import util.RestClientUtil;

@SpringBootApplication
@ComponentScan(basePackageClasses = {GenericController.class, RestClientUtil.class})
public class M1Application {

	public static void main(String[] args) {
		SpringApplication.run(M1Application.class, args);
	}

}

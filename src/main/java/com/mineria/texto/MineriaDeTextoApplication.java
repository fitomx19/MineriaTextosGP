package com.mineria.texto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MineriaDeTextoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MineriaDeTextoApplication.class, args);
	}

}

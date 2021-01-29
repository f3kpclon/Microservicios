package com.Prueba.microServicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.microservicio.commos.commons.Entities"})
public class MicroServiciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiciosApplication.class, args);
	}

}

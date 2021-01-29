package com.Prueba.ITEMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableCircuitBreaker
@EnableEurekaClient
//@RibbonClient(name="servicio-productos")
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EntityScan({"com.microservicio.commos.commons.Entities"})

public class MicroServiciosApplication {

	public static void main(String[] args) {

		SpringApplication.run(MicroServiciosApplication.class, args);
	}

}

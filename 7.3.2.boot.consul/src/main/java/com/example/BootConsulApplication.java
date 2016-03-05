package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PreDestroy;
import javax.ws.rs.container.PreMatching;

@SpringBootApplication
@EnableDiscoveryClient
public class BootConsulApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootConsulApplication.class);
	}
	@PreDestroy
	public void test(){

	}
}

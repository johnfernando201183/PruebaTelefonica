package com.example.PruebaTelefonica;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class PruebaTelefonicaApplication {

	public static void main(String[] args) throws UnknownHostException {
		Environment env = SpringApplication.run(PruebaTelefonicaApplication.class, args).getEnvironment();

		log.info("\n----------------------------------------------------------\n\t"
										+ "Application '{}' is running! Access URLs:\n\t"
										+ "Local: \t\thttp://localhost:{}\n\t"
										+ "External: \thttp://{}:{}\n\t",
						env.getProperty("spring.application.name"),
						env.getProperty("server.port"),
						InetAddress.getLocalHost().getHostAddress(),
						env.getProperty("server.port"));

		String configServerStatus = env.getProperty("configserver.status");
		log.info("\n----------------------------------------------------------\n\t"
										+ "Config Server: \t{}\n----------------------------------------------------------",
						configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);

	}

}

package com.example.socketdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SocketDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketDemoApplication.class, args);
	}

}

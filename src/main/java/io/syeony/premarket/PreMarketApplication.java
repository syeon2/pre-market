package io.syeony.premarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PreMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreMarketApplication.class, args);
	}
}

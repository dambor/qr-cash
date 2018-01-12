package br.com.dambor.qrcash.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.dambor.qrcash")
public class QrCashUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrCashUserServiceApplication.class, args);
	}
}

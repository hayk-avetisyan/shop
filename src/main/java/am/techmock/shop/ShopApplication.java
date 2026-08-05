package am.techmock.shop;

import am.technologies.smart.springkit.security.annotation.EnableWebPlatformSecurity;
import am.technologies.smart.springkit.web.annotation.EnableWebPlatformSpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableWebPlatformSecurity
@EnableWebPlatformSpa
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}

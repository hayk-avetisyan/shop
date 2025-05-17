package am.techmock.shop;

import am.techmock.sbwp.security.annotation.EnableWebPlatformSecurity;
import am.techmock.sbwp.web.annotation.EnableWebPlatformSpa;
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

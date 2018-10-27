package co.descubra.descubraapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import co.descubra.descubraapi.core.config.AppContext;

@SpringBootApplication
public class DescubraApiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DescubraApiApplication.class, args);
		
		AppContext.loadApplicationContext(ctx);
	}
}

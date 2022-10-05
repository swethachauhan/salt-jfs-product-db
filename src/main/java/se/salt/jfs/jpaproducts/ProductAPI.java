package se.salt.jfs.jpaproducts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductAPI {

	private static final Logger log = LoggerFactory.getLogger(ProductAPI.class);
	public static void main(String[] args) {
		SpringApplication.run(ProductAPI.class, args);
	}

}

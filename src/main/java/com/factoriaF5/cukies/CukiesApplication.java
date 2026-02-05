package com.factoriaF5.cukies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cloudinary.*;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CukiesApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));

		SpringApplication.run(CukiesApplication.class, args);
	}

}
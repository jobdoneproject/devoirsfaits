package fr.educ.devoirsfaits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevoirsfaitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevoirsfaitsApplication.class, args);
	}
}

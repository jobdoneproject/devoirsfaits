package fr.educ.devoirsfaits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan
@SpringBootApplication
@EnableJpaAuditing
public class DevoirsfaitsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DevoirsfaitsApplication.class, args);
	}


}

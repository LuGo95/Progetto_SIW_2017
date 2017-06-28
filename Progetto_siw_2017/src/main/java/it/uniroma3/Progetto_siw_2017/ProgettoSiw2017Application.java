package it.uniroma3.Progetto_siw_2017;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"it.uniroma3.Progetto_siw_2017", "it.uniroma3.Progetto_siw_2017.controller", "it.uniroma3.Progetto_siw_2017.security", "it.uniroma3.Progetto_siw_2017.service"})
@EnableJpaRepositories("it.uniroma3.Progetto_siw_2017.repository")
@EntityScan("it.uniroma3.Progetto_siw_2017.model")
public class ProgettoSiw2017Application extends SpringBootServletInitializer {

	//per la costruzione del war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProgettoSiw2017Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProgettoSiw2017Application.class, args);
	}
}

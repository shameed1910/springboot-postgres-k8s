package com.example.k8s.springbootpostgresk8s;

import com.example.k8s.springbootpostgresk8s.Model.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringbootPostgresK8sApplication {

	@Bean
	public AuditorAware<String> auditorAware(){

		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPostgresK8sApplication.class, args);
	}

}

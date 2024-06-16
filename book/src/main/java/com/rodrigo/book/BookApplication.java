package com.rodrigo.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rodrigo.book.infrastructure.repository.BookRepository;

@SpringBootApplication()
@EntityScan("com.rodrigo.book.infrastructure")
@EnableJpaRepositories(basePackageClasses = BookRepository.class)
@ComponentScan({"com.rodrigo.book.infrastructure.service", "com.rodrigo.book.application.controller"})
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}

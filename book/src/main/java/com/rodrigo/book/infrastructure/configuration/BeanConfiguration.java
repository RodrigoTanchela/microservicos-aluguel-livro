package com.rodrigo.book.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rodrigo.book.domain.adaptadores.services.BookServiceImp;
import com.rodrigo.book.domain.porta.interfaces.BookServicePort;
import com.rodrigo.book.domain.porta.repositories.BookRepositoryPort;

@Configuration
public class BeanConfiguration {
	
	@Bean
    BookServicePort bookService(BookRepositoryPort bookRepositoryPort) {
        return new BookServiceImp(bookRepositoryPort);
    }
}

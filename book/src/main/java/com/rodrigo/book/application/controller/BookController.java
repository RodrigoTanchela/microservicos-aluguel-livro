package com.rodrigo.book.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.book.domain.dto.BookDto;
import com.rodrigo.book.infrastructure.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("book")
@Tag(name = "Book", description = "Endpoints para livro")
public class BookController {
	
	@Autowired
	private BookService service;

	@GetMapping("/")
	@Operation(summary = "buscar todos os livros")
	public List<BookDto> buscarTodos() {
		return service.buscarTodos();
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "buscar livro por id")
	public BookDto buscarId(@PathVariable Long id) {
		return service.buscaId(id);
	}

	@PostMapping
	@Operation(summary = "inserir livro")
	public BookDto inserir(@RequestBody BookDto Book) {
		return service.inserir(Book);
	}

	@PutMapping
	@Operation(summary = "atualizar informação do livro")
	public BookDto atualizar(@RequestBody BookDto Book) {
		return service.atualizar(Book);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "deletar livro por id")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}")
	public BookDto inverterDisponibilidade(@PathVariable(value = "id") Long id) {
			return service.disponibilizar(id);
	}
}

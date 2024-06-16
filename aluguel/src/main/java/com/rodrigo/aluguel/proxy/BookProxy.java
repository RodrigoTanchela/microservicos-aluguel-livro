package com.rodrigo.aluguel.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rodrigo.aluguel.response.BookDto;

@FeignClient(name = "book")
public interface BookProxy {
	
	@GetMapping(value = "book/{id}")
	public BookDto buscarId(@PathVariable Long id);
	
	@GetMapping("book/")
	public List<BookDto> buscarTodos();
	
	@PatchMapping(value = "book/{id}")
	public BookDto inverterDisponibilidade(@PathVariable(value = "id") Long id);
	
	@PutMapping("book")
	public BookDto atualizar(@RequestBody BookDto Book);
}



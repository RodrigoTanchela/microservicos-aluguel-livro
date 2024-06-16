package com.rodrigo.pessoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.pessoa.model.Pessoa;
import com.rodrigo.pessoa.service.PessoaService;


@RestController
@RequestMapping("pessoa")
public class PessoaController {
	@Autowired
	private PessoaService service;

	@GetMapping(value = "/{id}")
	public Pessoa buscarId(@PathVariable(value = "id") Long id) {
		return service.buscaId(id);
	}
	
	@GetMapping(value = "username/{username}")
	public Pessoa buscarNomeUsuario(@PathVariable(value = "username") String username) {
		return service.buscaNomeUsuario(username);
	}
	
	@GetMapping("/")
	public List<Pessoa> buscarTodos() {
		return service.buscarTodos();
	}
	
	@PostMapping
	public Pessoa inserir(@RequestBody Pessoa pessoa) {
		return service.inserir(pessoa);
	}
	
	@GetMapping(value = "/hello")
	public String buscarId() {
		return "Hello world";
	}

	@PutMapping
	public Pessoa atualizar(@RequestBody Pessoa pessoa) {
		return service.atualizar(pessoa);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}

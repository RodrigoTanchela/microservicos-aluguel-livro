package com.rodrigo.clientes.springboot.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rodrigo.clientes.springboot.response.Pessoa;


@FeignClient(name = "pessoa")
public interface PessoaProxy {
	
	@PostMapping("pessoa")
	public Pessoa inserir(@RequestBody Pessoa Pessoa);
	
	@GetMapping(value = "pessoa/username/{username}")
	public Pessoa buscarNomeUsuario(@PathVariable(value = "username") String username);
	
	@DeleteMapping(value = "pessoa/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id);
}

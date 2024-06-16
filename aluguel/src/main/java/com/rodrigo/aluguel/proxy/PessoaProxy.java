package com.rodrigo.aluguel.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rodrigo.aluguel.response.Pessoa;

@FeignClient(name = "pessoa")
public interface PessoaProxy {
	
	@PostMapping("pessoa")
	public Pessoa inserir(@RequestBody Pessoa Pessoa);
	
	@GetMapping(value = "pessoa/{id}")
	public Pessoa buscarId(@PathVariable(value = "id") Long id);
}

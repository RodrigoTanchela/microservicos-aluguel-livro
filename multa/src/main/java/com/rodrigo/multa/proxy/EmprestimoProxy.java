package com.rodrigo.multa.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rodrigo.multa.response.Emprestimo;

@FeignClient(name = "emprestimo")
public interface EmprestimoProxy {
	
	@GetMapping(value = "emprestimo/{id}")
	public Emprestimo buscarId(@PathVariable Long id);

}
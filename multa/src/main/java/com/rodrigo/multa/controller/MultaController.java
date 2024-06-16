package com.rodrigo.multa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.multa.model.Multa;
import com.rodrigo.multa.proxy.EmprestimoProxy;
import com.rodrigo.multa.response.Emprestimo;
import com.rodrigo.multa.service.MultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("multa")
@Tag(name = "multa", description = "Endpoints para gerencimento de multa")
public class MultaController {
	
	@Autowired
	private MultaService service;
	
	@Autowired
	private EmprestimoProxy emprestimoProxy;

	@GetMapping("/")
	@Operation(summary = "buscar todas as multas")
	public List<Multa> buscarTodos() {
		return service.buscarTodos();
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "buscar multa por id")
	public Multa buscarId(@PathVariable Long id) {
		return service.buscaId(id);
	}
	
	@PostMapping(value = "geraMulta/{idEmprestimo}/{idMulta}")
	@Operation(summary = "gera multa ")
	@Transactional
	@SuppressWarnings("rawtypes")
	public ResponseEntity geraMulta(@PathVariable(value = "idEmprestimo") Long idEmprestimo, @PathVariable(value = "idMulta") Long idMulta) {
		Emprestimo emprestimo = emprestimoProxy.buscarId(idEmprestimo);
		Long dias = emprestimo.calcularDiferencaEmDias(emprestimo.getDiaTermino(), new Date());
		int  diasAtrasados = dias.intValue();
		double multa = service.calcularMultaPorAtraso(idMulta, diasAtrasados);
		if(multa <= 0 || diasAtrasados <= 0) {
			return ResponseEntity.badRequest().body("Não é possivel gera multa pois o livro foi entrega na data correta");
		}
		return ResponseEntity.ok().body(multa);
	}

	@PostMapping
	@Operation(summary = "inserir multa")
	public Multa inserir(@RequestBody Multa Multa) {
		return service.inserir(Multa);
	}

	@PutMapping
	@Operation(summary = "atualizar multa")
	public Multa atualizar(@RequestBody Multa Multa) {
		return service.atualizar(Multa);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "deleta as multas")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}

package com.rodrigo.aluguel.controller;

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

import com.rodrigo.aluguel.model.Emprestimo;
import com.rodrigo.aluguel.proxy.BookProxy;
import com.rodrigo.aluguel.proxy.PessoaProxy;
import com.rodrigo.aluguel.response.BookDto;
import com.rodrigo.aluguel.services.EmprestimoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;


@RestController
@RequestMapping("emprestimo")
@Tag(name = "emprestimo", description = "Endpoints para gerencimento de emprestimo")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoService service;
	
	@Autowired
	private BookProxy bookProxy;
	
	@Autowired
	private PessoaProxy pessoaProxy;

	@GetMapping("/")
	@Operation(summary = "buscar todos os emprestimos")
	public List<Emprestimo> buscarTodos() {
		return service.buscarTodos();
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/alugarLivro")
	@Operation(summary = "Aluguel de livros")
	@Transactional
	public ResponseEntity aluguel(@RequestBody Emprestimo emprestimo){
		 var book = bookProxy.buscarId(emprestimo.getIdlivro());
		 var pessoa = pessoaProxy.buscarId(emprestimo.getIdusuario());
		 
		 if(!verificarLivroDisponivel(book, emprestimo)) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível criar o empréstimo verifique as informações");
		 }
		 service.inserir(emprestimo);
		 if(!book.isdigital() && book.isDiposnivel()) {
			 book.setDisponivel(false);
			 book.setDataDisponivel(emprestimo.getDiaTermino());
			 bookProxy.atualizar(book);
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(emprestimo);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "buscar emprestimo por id")
	public Emprestimo buscarId(@PathVariable Long id) {
		return service.buscaId(id);
	}

	@PutMapping
	@Operation(summary = "atualizar emprestimo")
	public Emprestimo atualizar(@RequestBody Emprestimo Emprestimo) {
		return service.atualizar(Emprestimo);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "deletar emprestimo por id")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "devolve livro e seta as informações")
	@PostMapping("/devolver/{idEmprestimo}")
	@Transactional
	public BookDto devolverLivro(@PathVariable(value = "idEmprestimo") Long idEmprestimo) {
		var entity = service.devolucaoEmprestimo(idEmprestimo);
	    var book = bookProxy.buscarId(entity.getIdlivro());
	    book.setDisponivel(true);
	    book.setDataDisponivel(new Date());
	    bookProxy.atualizar(book);
	    return book;
	}
	
	public boolean verificarLivroDisponivel(BookDto book, Emprestimo emprestimo) {
	    return book.isdigital() || book.isDiposnivel() || emprestimo.getDiaInicio().compareTo(book.getDataDisponivel()) > 0;
	}
	
}

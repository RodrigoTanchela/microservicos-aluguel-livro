package com.rodrigo.pessoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.pessoa.exceptions.ResourceNotFoundException;
import com.rodrigo.pessoa.model.Pessoa;
import com.rodrigo.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;

	public PessoaService(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Pessoa buscaId(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
	}
	
	public Pessoa buscaNomeUsuario(String username) {
		var pessoa = repository.findByUsername(username);
		if (pessoa != null) {
			return pessoa;
		}
		throw new ResourceNotFoundException("Nenhum registro encontrado para este nome");
	}
	
	public List<Pessoa> buscarTodos() {
		return repository.findAll();
	}
	
	
	public Pessoa inserir(Pessoa pessoa) {
		 return repository.save(pessoa);
	}
	
	public Pessoa atualizar(Pessoa pessoa) {
		var usuarioData = repository.findById(pessoa.getId()).orElseThrow(()-> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		usuarioData.setUsername(pessoa.getUsername());
		usuarioData.setNomeCompleto(pessoa.getNomeCompleto());
		usuarioData.setCpf(pessoa.getCpf());
		usuarioData.setEmail(pessoa.getEmail());
		usuarioData.setTelefone(pessoa.getTelefone());
		return repository.save(usuarioData);
	}
	
	public Pessoa deletar(Long id) {
		 var Pessoa = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		 repository.delete(Pessoa);
		 return Pessoa;
	}
}

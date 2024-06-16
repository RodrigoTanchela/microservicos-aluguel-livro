package com.rodrigo.clientes.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.clientes.springboot.model.Usuario;
import com.rodrigo.clientes.springboot.model.vo.v1.security.AccountCredentialsVO;
import com.rodrigo.clientes.springboot.proxy.PessoaProxy;
import com.rodrigo.clientes.springboot.response.Pessoa;
import com.rodrigo.clientes.springboot.service.AuthServices;
import com.rodrigo.clientes.springboot.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/auth")
@Tag(name = "Usuario", description = "Endpoints para autenticação de usuario")
public class AuthController {
	
	@Autowired
	AuthServices service;
	
	@Autowired
	PessoaProxy pessoaProxy;
	
	@Autowired
	UsuarioService usuarioService;
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Autenticando e retornando token")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {
			if(checkParametersNull(data)) {
				ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de usuário inválida");
			}
			var token = service.signin(data);
			if(token == null) {
				ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de usuário inválida");
			}
			return token;
		} catch (Exception e) {
			System.out.println("Erro ao efetuar login: " +data.getUsername() + data.getPassword());
		    e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de usuário inválida");	
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/register")
	public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario) {
		try {
			service.cadastrarUsuario(usuario);
			Pessoa pessoa = new Pessoa(usuario.getUsername(), usuario.getFullName(), 
					usuario.getCpf(), usuario.getEmail(), usuario.getTelefone(), usuario.getEnabled());
			pessoaProxy.inserir(pessoa);
			return ResponseEntity.status(HttpStatus.OK).body("Usuario cadastrado com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar usuario verifique as informações e tente novamente: " +usuario.getUsername());
		    e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar usuario verifique as informações: "+ usuario.getUsername());

	}
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Refresh token e retorno do token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		if (checkParametersNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de usuário inválida!");
		var token = service.refreshToken(username, refreshToken);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de usuário inválida!");
		return token;
	}
	
	@DeleteMapping(value = "/{username}")
	@Transactional
	public ResponseEntity<?> deleteUsername(@PathVariable(value = "username") String username) {
		var pessoa = pessoaProxy.buscarNomeUsuario(username);
		var usuario = usuarioService.buscarNomeUsuario(username);
		usuarioService.deletar(usuario.getId());
		pessoaProxy.delete(pessoa.getId());
		return ResponseEntity.noContent().build();
	}
	
	private boolean checkParametersNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() ||
				username == null || username.isBlank();
}

	private boolean checkParametersNull(AccountCredentialsVO data) {
			return data == null || data.getUsername() == null || data.getUsername().isBlank()
					 || data.getPassword() == null || data.getPassword().isBlank();
	}

}

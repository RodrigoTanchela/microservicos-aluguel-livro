package com.rodrigo.clientes.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rodrigo.clientes.springboot.model.Usuario;
import com.rodrigo.clientes.springboot.repository.UsuarioRepository;
import com.rodrigo.clientes.springboot.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByUsername(username);
		if(usuario != null) {
			return usuario;
		}else {
	        throw new UsernameNotFoundException("Usuario com o nome " + username + " não encontrado!");
	    }
	}
	
	public Usuario buscarNomeUsuario(String username) {
		Usuario usuario = repository.findByUsername(username);
		if(usuario != null) {
			return usuario;
		}else {
	        throw new UsernameNotFoundException("Usuario com o nome " + username + " não encontrado!");
	    }
	}
	
	public Usuario deletar(Long id) {
		 var usuario = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		 repository.delete(usuario);
		 return usuario;
	}

}

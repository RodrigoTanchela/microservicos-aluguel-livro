package com.rodrigo.clientes.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigo.clientes.springboot.exceptions.ResourceNotFoundException;
import com.rodrigo.clientes.springboot.model.Usuario;
import com.rodrigo.clientes.springboot.model.vo.v1.security.AccountCredentialsVO;
import com.rodrigo.clientes.springboot.model.vo.v1.security.TokenVO;
import com.rodrigo.clientes.springboot.repository.UsuarioRepository;
import com.rodrigo.clientes.springboot.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {
	
	@Autowired
	private AuthenticationManager authentication;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsVO data) {
		try {
				var username = data.getUsername();
				var password = data.getPassword();
				authentication.authenticate(
						new UsernamePasswordAuthenticationToken(username, password));
				
				var user = repository.findByUsername(username);
				
				TokenVO token = new TokenVO();
				if (user != null) {
					token = tokenProvider.createAccessToken(username, user.getRoles());
				} else {
					throw new UsernameNotFoundException("Usuario "+ username+ " não encontrado!");
				}
				return ResponseEntity.ok(token);
				
			}catch (Exception e) {
				throw new BadCredentialsException("Nome de usuario/senha invalido!");
			}
				
	}
	
	public Usuario cadastrarUsuario(Usuario user) {
        String senhaCriptografada = passwordEncoder.encode(user.getPassword());
        senhaCriptografada = removerPrefixo(senhaCriptografada);
        user.setPassword(senhaCriptografada);

        // Salve o usuário no banco de dados
        return repository.save(user);
    }
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
		var user = repository.findByUsername(username);
		
		var tokenVO = new TokenVO();
		if(user != null) {
			tokenVO = tokenProvider.refreshToken(username);
		} else {
			throw new UsernameNotFoundException("Usuario "+ username+ " não encontrado!");
		}
		return ResponseEntity.ok(tokenVO);
	}
	
	private String removerPrefixo(String senha) {
	    if (senha.startsWith("{pbkdf2}")) {
	        return senha.substring("{pbkdf2}".length());
	    }
	    return senha;
	}
}

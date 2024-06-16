package com.rodrigo.pessoa.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "username", unique = true, nullable = true, length = 255)
	private String username;
	
	@Column (name = "nomeCompleto")
	private String nomeCompleto;
	
	@Column(name = "cpf", unique = true, nullable = false, length = 14)
	private String cpf;
	
	@Column(name = "email", unique = true, nullable = true, length = 60)
	private String email;
	
	@Column(name = "telefone", unique = true, nullable = true, length = 20)
	private String telefone;
	
	@Column(name = "ativo")
	private boolean ativo;

    // Construtor padrão
    public Pessoa() {
    }

    // Construtor com argumentos (sem id)
    public Pessoa(String username, String nomeCompleto, String cpf, String email, String telefone, Boolean ativo) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    // Construtor com todos os argumentos
    public Pessoa(Long id, String username, String nomeCompleto, String cpf, String email, String telefone, Boolean ativo) {
        this.id = id;
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

	@Override
	public int hashCode() {
		return Objects.hash(ativo, cpf, email, id, nomeCompleto, telefone, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return ativo == other.ativo && Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nomeCompleto, other.nomeCompleto)
				&& Objects.equals(telefone, other.telefone) && Objects.equals(username, other.username);
	}
}

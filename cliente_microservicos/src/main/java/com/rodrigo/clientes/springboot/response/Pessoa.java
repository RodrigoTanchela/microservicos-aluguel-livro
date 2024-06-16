package com.rodrigo.clientes.springboot.response;

import java.util.Objects;

public class Pessoa {
    
    private Long id;
    private String username;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String telefone;
    private Boolean ativo;

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
        return Objects.hash(ativo, cpf, email, id, nomeCompleto, username, telefone);
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
        return Objects.equals(ativo, other.ativo) && Objects.equals(cpf, other.cpf)
                && Objects.equals(email, other.email) && Objects.equals(id, other.id)
                && Objects.equals(nomeCompleto, other.nomeCompleto) && Objects.equals(username, other.username)
                && Objects.equals(telefone, other.telefone);
    }
}

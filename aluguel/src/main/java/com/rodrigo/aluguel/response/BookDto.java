package com.rodrigo.aluguel.response;

import java.util.Date;
import java.util.Objects;


public class BookDto {
	
	private Long id;
	private String titulo;
	private String descricao;
	private Double preco;
	private Boolean digital;
	private Boolean disponivel;
	private Date dataDisponivel;
	
	public BookDto() {
		
	}
	
	public BookDto(Long id, String titulo, String descricao, Double preco, Boolean digital, Boolean disponivel,
			Date dataDisponivel) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.digital = digital;
		this.disponivel = disponivel;
		this.dataDisponivel = dataDisponivel;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dataDisponivel, descricao, disponivel, digital, id, preco, titulo);
	}

	public Boolean getDigital() {
		return digital;
	}

	public void setDigital(Boolean digital) {
		this.digital = digital;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Date getDataDisponivel() {
		return dataDisponivel;
	}

	public void setDataDisponivel(Date dataDisponivel) {
		this.dataDisponivel = dataDisponivel;
	}
	
	public boolean isDiposnivel() {
		return this.disponivel;
	}
	
	public boolean isdigital() {
		return this.digital;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDto other = (BookDto) obj;
		return Objects.equals(dataDisponivel, other.dataDisponivel) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(disponivel, other.disponivel) && Objects.equals(digital, other.digital)
				&& Objects.equals(id, other.id) && Objects.equals(preco, other.preco)
				&& Objects.equals(titulo, other.titulo);
	}
	
}

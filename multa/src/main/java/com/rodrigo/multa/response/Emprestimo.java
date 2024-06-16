package com.rodrigo.multa.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String informacoes;
	private Long idusuario;
	private Long idlivro;
	private Date diaInicio;
	private Date diaTermino;
	private Date diaDevolucao;

	public Emprestimo() {
		super();
	}
	
	public Emprestimo(String titulo, String informacoes, Long idusuario, Long idlivro, Date diaInicio, Date diaTermino,
			Date diaDevolucao) {
		super();
		this.titulo = titulo;
		this.informacoes = informacoes;
		this.idusuario = idusuario;
		this.idlivro = idlivro;
		this.diaInicio = diaInicio;
		this.diaTermino = diaTermino;
		this.diaDevolucao = diaDevolucao;
	}

	public Emprestimo(Long id, String titulo, String informacoes, Long idusuario, Long idlivro, Date diaInicio,
			Date diaTermino, Date diaDevolucao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.informacoes = informacoes;
		this.idusuario = idusuario;
		this.idlivro = idlivro;
		this.diaInicio = diaInicio;
		this.diaTermino = diaTermino;
		this.diaDevolucao = diaDevolucao;
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

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public Long getIdlivro() {
		return idlivro;
	}

	public void setIdlivro(Long idlivro) {
		this.idlivro = idlivro;
	}

	public Date getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(Date diaInicio) {
		this.diaInicio = diaInicio;
	}

	public Date getDiaTermino() {
		return diaTermino;
	}

	public void setDiaTermino(Date diaTermino) {
		this.diaTermino = diaTermino;
	}

	public Date getDiaDevolucao() {
		return diaDevolucao;
	}

	public void setDiaDevolucao(Date diaDevolucao) {
		this.diaDevolucao = diaDevolucao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diaDevolucao, diaInicio, diaTermino, id, idlivro, idusuario, informacoes, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(diaDevolucao, other.diaDevolucao) && Objects.equals(diaInicio, other.diaInicio)
				&& Objects.equals(diaTermino, other.diaTermino) && Objects.equals(id, other.id)
				&& Objects.equals(idlivro, other.idlivro) && Objects.equals(idusuario, other.idusuario)
				&& Objects.equals(informacoes, other.informacoes) && Objects.equals(titulo, other.titulo);
	}

	public long calcularDiferencaEmDias(Date dataInicio, Date dataDisponivel) {
        long diffInMillies = dataDisponivel.getTime() -  dataInicio.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}

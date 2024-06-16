package com.rodrigo.multa.model;

import java.io.Serializable;

import com.rodrigo.multa.model.enums.MultaType;
import com.rodrigo.multa.service.CalculadoraMultaService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "multa")
public class Multa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "valorDiario", nullable = false, precision = 2)
	private double valorDiario;
	
	@Column(name = "tipo", nullable = false, length = 50)
	private MultaType tipo;
	
	public Multa(Long id, int dias, double valorDiario, MultaType tipo) {
		this.id = id;
		this.valorDiario = valorDiario;
		this.tipo = tipo;
	}

	public Multa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValorDiario() {
		return valorDiario;
	}

	public void setValorDiario(double valorDiario) {
		this.valorDiario = valorDiario;
	}

	public MultaType getTipo() {
		return tipo;
	}

	public void setTipo(MultaType tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double multar(int dias, CalculadoraMultaService calculadora) {
		return calculadora.calcularMulta(dias, valorDiario);
	}
	
}

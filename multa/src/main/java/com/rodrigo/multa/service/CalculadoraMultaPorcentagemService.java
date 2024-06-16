package com.rodrigo.multa.service;

public class CalculadoraMultaPorcentagemService implements CalculadoraMultaService {
	private static final double PERCENTUAL = 100;
	
	@Override
	public double calcularMulta(int diasAtraso, double valorDiario) {
		double valorMulta = valorDiario + (valorDiario / PERCENTUAL);
		return valorMulta;
	}

}

package com.rodrigo.multa.service;

public class CalculadoraMultaPorcentagemComposta implements CalculadoraMultaService {
	private static final double PERCENTUAL = 100;
	private static final double PERCENTUALCOMPOSTO = 0.3;

	@Override
	public double calcularMulta(int diasAtraso, double valorDiario) {
		
        double valorComPorcentagem1 = valorDiario * (valorDiario / PERCENTUAL); 
        double valorMulta = valorDiario + (valorComPorcentagem1 * PERCENTUALCOMPOSTO) * diasAtraso;
        return valorMulta;
	}

}

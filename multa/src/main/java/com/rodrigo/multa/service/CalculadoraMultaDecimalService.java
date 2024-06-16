package com.rodrigo.multa.service;

import org.springframework.stereotype.Service;


@Service
public class CalculadoraMultaDecimalService implements CalculadoraMultaService {

	@Override
	public double calcularMulta(int diasAtraso, double valorDiario) {
		double valorMulta = valorDiario + valorDiario * diasAtraso;
		return valorMulta;
	}

}

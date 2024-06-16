package com.rodrigo.multa.utils;

import java.util.HashMap;
import java.util.Map;

import com.rodrigo.multa.model.enums.MultaType;
import com.rodrigo.multa.service.CalculadoraMultaDecimalService;
import com.rodrigo.multa.service.CalculadoraMultaPorcentagemComposta;
import com.rodrigo.multa.service.CalculadoraMultaPorcentagemService;
import com.rodrigo.multa.service.CalculadoraMultaService;

public class MultaMapper {
	
	 private static final Map<MultaType, CalculadoraMultaService> serviceMap = new HashMap<>();
	 
	 static {
	        serviceMap.put(MultaType.PORCENTAGEM, new CalculadoraMultaPorcentagemService());
	        serviceMap.put(MultaType.DECIMAL, new CalculadoraMultaDecimalService());
	        serviceMap.put(MultaType.COMPOSTO, new CalculadoraMultaPorcentagemComposta());
	 }
	 
	 public static CalculadoraMultaService getCalculadoraService(MultaType tipo) {
	        return serviceMap.get(tipo);
	 }
}
